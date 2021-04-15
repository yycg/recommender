package com.bupt.recommender.controller;

import com.bupt.recommender.common.ResultBean;
import com.bupt.recommender.dto.LawCaseDTO;
import com.bupt.recommender.entity.AdminUserPO;
import com.bupt.recommender.entity.LawPO;
import com.bupt.recommender.entity.LawSpecialtyPO;
import com.bupt.recommender.entity.LawTypePO;
import com.bupt.recommender.service.LawService;
import com.bupt.recommender.service.UserService;
import com.bupt.recommender.utils.LawConverter;
import com.bupt.recommender.vo.*;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LawController {
    @Autowired
    private LawService lawService;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(LawController.class);

    @RequestMapping(path="/law/search", method=RequestMethod.POST)
    public ResultBean<LawsRespVO> searchLaws(
            @RequestParam(value="keyword", required=true) String keyword,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("searchLaws: keyword {}, start {}, count {}", keyword, start, count);
        try {
            LawsRespVO lawsRespVO = new LawsRespVO();
            lawsRespVO.setLawPOs(lawService.searchLaws(keyword, start, count));
            lawsRespVO.setStart(start);
            lawsRespVO.setCount(count);
            lawsRespVO.setTotal(lawService.countLawsByKeyword(keyword));
            return new ResultBean<>(lawsRespVO);
        } catch (Exception e) {
            logger.warn("searchLaws: keyword {}, start {}, count {}", keyword, start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/recommend", method=RequestMethod.POST)
    public ResultBean<LawsRespVO> recommendLaws(
            @RequestParam(value="lawTitles", required=false) String[] lawTitles,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("recommendLaws: lawTitles {}, start {}, count {}", lawTitles, start, count);
        try {
            LawsRespVO lawsRespVO = new LawsRespVO();
            lawsRespVO.setLawPOs(lawService.recommendLaws(
                    lawTitles == null ? new String[0] : lawTitles, start, count));
            lawsRespVO.setStart(start);
            lawsRespVO.setCount(count);
            lawsRespVO.setTotal(100);
            return new ResultBean<>(lawsRespVO);
        } catch (Exception e) {
            logger.warn("recommendLaws: lawTitles {}, start {}, count {}", lawTitles, start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/personalRecommend", method=RequestMethod.POST)
    public ResultBean<LawsRespVO> personalRecommendLaws(
            @RequestParam(value="username", required=true) String username,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("personalRecommendLaws: username {}, start {}, count {}", username, start, count);
        try {
            LawsRespVO lawsRespVO = new LawsRespVO();
            AdminUserPO adminUserPO = userService.findByUsername(username);
            List<String> lawIds = lawService.getLawIdsByUserId(adminUserPO.getId());
            String[] lawTitles = lawIds.toArray(new String[0]);
            lawsRespVO.setLawPOs(lawService.recommendLaws(lawTitles, start, count));
            lawsRespVO.setStart(start);
            lawsRespVO.setCount(count);
            lawsRespVO.setTotal(100);
            return new ResultBean<>(lawsRespVO);
        } catch (Exception e) {
            logger.warn("personalRecommendLaws: username {}, start {}, count {}", username, start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/display", method=RequestMethod.GET)
    public ResultBean<List<LawTreeNodeRespVO>> displayLawTree() {
        logger.info("displayLawTree");
        try {
            return new ResultBean<>(lawSpecialtyDFS());
        } catch (Exception e) {
            logger.warn("displayLawTree exception", e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/{id}", method=RequestMethod.GET)
    public ResultBean<LawRespVO> getLawDetail(@PathVariable(name="id", required=true) String id) {
        logger.info("getLawDetail: id {}", id);
        try {
            String username = (String)SecurityUtils.getSubject().getPrincipal();
            if (!StringUtils.isEmpty(username)) {
                int userId = userService.findByUsername(username).getId();
                lawService.insertUserLaw(userId, id);
            }

            LawRespVO lawRespVO = new LawRespVO();
            lawRespVO.setLawPO(lawService.getLawById(id));
            return new ResultBean<>(lawRespVO);
        } catch (Exception e) {
            logger.warn("getLawDetail exception: id {}", id, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/relatedCases/{id}", method=RequestMethod.GET)
    public ResultBean<LawCasesRespVO> getRelatedCasesOfLaw(@PathVariable(name="id", required=true) String id) {
        logger.info("getRelatedCasesOfLaw: id {}", id);
        try {
            LawCasesRespVO lawCasesRespVO = new LawCasesRespVO();
            List<LawCaseDTO> lawCaseDTOs = lawService.getRelatedCasesOfLaw(id);
            lawCasesRespVO.setLawCaseDTOs(lawCaseDTOs);
            return new ResultBean<>(lawCasesRespVO);
        } catch (Exception e) {
            logger.warn("getRelatedCasesOfLaw exception: id {}", id, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/lawsOfRelatedCases/{id}", method=RequestMethod.GET)
    public ResultBean<LawsRespVO> getLawsOfRelatedCases(@PathVariable(name="id", required=true) String id) {
        logger.info("getLawsOfRelatedCases: id {}", id);
        try {
            LawsRespVO lawsRespVO = new LawsRespVO();
            List<LawPO> lawPOLists = lawService.getLawsOfRelatedCases(id);
            lawsRespVO.setLawPOs(lawPOLists);
            lawsRespVO.setCount(lawPOLists.size());
            lawsRespVO.setStart(0);
            lawsRespVO.setTotal(lawPOLists.size());
            return new ResultBean<>(lawsRespVO);
        } catch (Exception e) {
            logger.warn("getLawsOfRelatedCases exception: id {}", id, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/recommendExplanation", method=RequestMethod.POST)
    public ResultBean<LawsRespVO> getRecommendExplanation(
            @RequestParam(value="lawTitles", required=true) String[] lawTitles) {
        logger.info("getRecommendExplanation: lawTitles {}", lawTitles);
        try {
            LawsRespVO lawsRespVO = new LawsRespVO();
            List<LawPO> lawPOLists = new ArrayList<>();
            for (String lawTitle : lawTitles) {
                lawPOLists.addAll(lawService.getLawsOfRelatedCases(lawTitle));
            }
            lawPOLists = lawPOLists.stream().filter(LawConverter.distinctByKey(LawPO::getId)).
                    collect(Collectors.toList());
            lawsRespVO.setLawPOs(lawPOLists);
            lawsRespVO.setCount(lawPOLists.size());
            lawsRespVO.setStart(0);
            lawsRespVO.setTotal(lawPOLists.size());
            return new ResultBean<>(lawsRespVO);
        } catch (Exception e) {
            logger.warn("getRecommendExplanation exception: lawTitles {}", lawTitles, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/personalRecommendExplanation", method=RequestMethod.GET)
    public ResultBean<LawsRespVO> getPersonalRecommendExplanation(
            @RequestParam(value="username", required=true) String username) {
        logger.info("getPersonalRecommendExplanation: username {}", username);
        try {
            LawsRespVO lawsRespVO = new LawsRespVO();
            AdminUserPO adminUserPO = userService.findByUsername(username);
            List<String> lawIds = lawService.getLawIdsByUserId(adminUserPO.getId());
            List<LawPO> lawPOLists = new ArrayList<>();
            for (String lawId : lawIds) {
                lawPOLists.addAll(lawService.getLawsOfRelatedCases(lawId));
            }
            lawPOLists = lawPOLists.stream().filter(LawConverter.distinctByKey(LawPO::getId)).
                    collect(Collectors.toList());
            lawsRespVO.setLawPOs(lawPOLists);
            lawsRespVO.setCount(lawPOLists.size());
            lawsRespVO.setStart(0);
            lawsRespVO.setTotal(lawPOLists.size());
            return new ResultBean<>(lawsRespVO);
        } catch (Exception e) {
            logger.warn("getPersonalRecommendExplanation exception: username {}", username, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/specialty", method=RequestMethod.GET)
    public ResultBean<LawsRespVO> getLawsBySpecialtyId(
            @RequestParam(value="specialtyId", required=true) String specialtyId,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("getLawsBySpecialtyId: specialtyId {}, start {}, count {}", specialtyId, start, count);
        try {
            LawsRespVO lawsRespVO = new LawsRespVO();
            lawsRespVO.setLawPOs(lawService.getLawsBySpecialtyId(specialtyId, start, count));
            lawsRespVO.setStart(start);
            lawsRespVO.setCount(count);
            lawsRespVO.setTotal(lawService.countLawsBySpecialtyId(specialtyId));
            return new ResultBean<>(lawsRespVO);
        } catch (Exception e) {
            logger.warn("getLawsBySpecialtyId: specialtyId {}, start {}, count {}", specialtyId, start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/specialties", method=RequestMethod.GET)
    public ResultBean<LawSpecialtiesVO> getAllSpecialties() {
        logger.info("getSpecialties");
        try {
            LawSpecialtiesVO lawSpecialtiesVO = new LawSpecialtiesVO();
            lawSpecialtiesVO.setLawSpecialtyPOs(lawService.getLawSpecialties());
            return new ResultBean<>(lawSpecialtiesVO);
        } catch (Exception e) {
            logger.warn("getSpecialties", e);
            return new ResultBean<>(e);
        }
    }

    private List<LawTreeNodeRespVO> lawSpecialtyDFS() throws Exception {
        List<LawTreeNodeRespVO> lawTreeNodeRespVOS = new ArrayList<>();
        for (LawSpecialtyPO lawSpecialtyPO: lawService.getLawSpecialties()) {
            LawTreeNodeRespVO lawTreeNodeRespVO = new LawTreeNodeRespVO();
            lawTreeNodeRespVO.setTitle(lawSpecialtyPO.getName());
            lawTreeNodeRespVO.setKey(lawSpecialtyPO.getId());
            lawTreeNodeRespVO.setChildren(lawTypeDFS(lawSpecialtyPO.getId(), lawSpecialtyPO.getId()));
            lawTreeNodeRespVOS.add(lawTreeNodeRespVO);
        }
        return lawTreeNodeRespVOS;
    }

    private List<LawTreeNodeRespVO> lawTypeDFS(String specialtyId, String parentId) throws Exception {
        List<LawTreeNodeRespVO> lawTreeNodeRespVOS = new ArrayList<>();
        for (LawTypePO lawTypePO: lawService.getLawTypes(specialtyId, parentId)) {
            LawTreeNodeRespVO lawTreeNodeRespVO = new LawTreeNodeRespVO();
            lawTreeNodeRespVO.setTitle(lawTypePO.getName());
            lawTreeNodeRespVO.setKey(lawTypePO.getId());

            List<LawTreeNodeRespVO> children = lawTypeDFS(specialtyId, lawTypePO.getId());
            if (CollectionUtils.isEmpty(children)) {
                lawTreeNodeRespVO.setChildren(lawDFS(specialtyId, lawTypePO.getId()));
            } else {
                lawTreeNodeRespVO.setChildren(children);
            }

            lawTreeNodeRespVOS.add(lawTreeNodeRespVO);
        }
        return lawTreeNodeRespVOS;
    }

    private List<LawTreeNodeRespVO> lawDFS(String specialtyId, String typeId) throws Exception {
        List<LawTreeNodeRespVO> lawTreeNodeRespVOS = new ArrayList<>();
        for (LawPO lawPO: lawService.getLaws(specialtyId, typeId)) {
            LawTreeNodeRespVO lawTreeNodeRespVO = new LawTreeNodeRespVO();
            lawTreeNodeRespVO.setTitle(lawPO.getCheckContent());
            lawTreeNodeRespVO.setKey(lawPO.getId());
            lawTreeNodeRespVOS.add(lawTreeNodeRespVO);
        }
        return lawTreeNodeRespVOS;
    }
}
