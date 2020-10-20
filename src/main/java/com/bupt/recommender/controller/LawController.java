package com.bupt.recommender.controller;

import com.bupt.recommender.common.ResultBean;
import com.bupt.recommender.entity.LawPO;
import com.bupt.recommender.entity.LawSpecialtyPO;
import com.bupt.recommender.entity.LawTypePO;
import com.bupt.recommender.service.LawService;
import com.bupt.recommender.vo.LawTreeNodeRespVO;
import com.bupt.recommender.vo.LawsRespVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LawController {
    @Autowired
    private LawService lawService;

    private static final Logger logger = LoggerFactory.getLogger(LawController.class);

    @RequestMapping(path="/law/search", method=RequestMethod.POST)
    public ResultBean<LawsRespVO> searchLaws(
            @RequestParam(value="keyword", required=true) String keyword,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("searchLaws: keyword {}, start {}, count {}", keyword, start, count);
        try {
            // TODO implement this, return fake data temporarily
            LawsRespVO lawsRespVO = new LawsRespVO();
            List<LawPO> lawPOs = lawService.searchLaws(keyword);
            lawsRespVO.setLawPOs(lawPOs.subList(start, start+count));
            lawsRespVO.setStart(start);
            lawsRespVO.setCount(count);
            lawsRespVO.setTotal(lawPOs.size());
            return new ResultBean<>(lawsRespVO);
        } catch (Exception e) {
            logger.warn("searchLaws: keyword {}, start {}, count {}", keyword, start, count, e);
            return new ResultBean<>(e);
        }
    }

    @RequestMapping(path="/law/recommend", method=RequestMethod.POST)
    public ResultBean<LawsRespVO> recommendLaws(
            @RequestParam(value="algorithm", required=true) String algorithm,
            @RequestParam(value="lawTitles", required=false) String[] lawTitles,
            @RequestParam(value="start", required=true) int start,
            @RequestParam(value="count", required=true) int count) {
        logger.info("recommendLaws: algorithm {}, lawTitles {}, start {}, count {}",
                algorithm, lawTitles, start, count);
        try {
            // TODO implement this, return fake data temporarily
            LawsRespVO lawsRespVO = new LawsRespVO();
            List<LawPO> lawPOs = lawService.searchLaws("卫生");
            lawsRespVO.setLawPOs(lawPOs.subList(start, Math.min(start+count, lawPOs.size())));
            lawsRespVO.setStart(start);
            lawsRespVO.setCount(count);
            lawsRespVO.setTotal(lawPOs.size());
            return new ResultBean<>(lawsRespVO);
        } catch (Exception e) {
            logger.warn("recommendLaws: algorithm {}, lawTitles {}, start {}, count {}",
                    algorithm, lawTitles, start, count, e);
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
