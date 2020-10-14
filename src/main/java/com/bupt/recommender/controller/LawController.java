package com.bupt.recommender.controller;

import com.bupt.recommender.common.ResultBean;
import com.bupt.recommender.entity.LawPO;
import com.bupt.recommender.entity.LawSpecialtyPO;
import com.bupt.recommender.entity.LawTypePO;
import com.bupt.recommender.service.LawService;
import com.bupt.recommender.vo.LawTreeNodeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LawController {
    @Autowired
    private LawService lawService;

    private static final Logger logger = LoggerFactory.getLogger(LawController.class);

    @RequestMapping(path="/law/display", method= RequestMethod.GET)
    public ResultBean<List<LawTreeNodeVO>> displayLawTree() {
        logger.info("displayLawTree");
        try {
            return new ResultBean<>(lawSpecialtyDFS());
        } catch (Exception e) {
            logger.warn("displayLawTree exception", e);
            return new ResultBean<>(e);
        }
    }

    private List<LawTreeNodeVO> lawSpecialtyDFS() throws Exception {
        List<LawTreeNodeVO> lawTreeNodeVOs = new ArrayList<>();
        for (LawSpecialtyPO lawSpecialtyPO: lawService.getLawSpecialties()) {
            LawTreeNodeVO lawTreeNodeVO = new LawTreeNodeVO();
            lawTreeNodeVO.setTitle(lawSpecialtyPO.getName());
            lawTreeNodeVO.setKey(lawSpecialtyPO.getId());
            lawTreeNodeVO.setChildren(lawTypeDFS(lawSpecialtyPO.getId(), lawSpecialtyPO.getId()));
            lawTreeNodeVOs.add(lawTreeNodeVO);
        }
        return lawTreeNodeVOs;
    }

    private List<LawTreeNodeVO> lawTypeDFS(String specialtyId, String parentId) throws Exception {
        List<LawTreeNodeVO> lawTreeNodeVOs = new ArrayList<>();
        for (LawTypePO lawTypePO: lawService.getLawTypes(specialtyId, parentId)) {
            LawTreeNodeVO lawTreeNodeVO = new LawTreeNodeVO();
            lawTreeNodeVO.setTitle(lawTypePO.getName());
            lawTreeNodeVO.setKey(lawTypePO.getId());

            List<LawTreeNodeVO> children = lawTypeDFS(specialtyId, lawTypePO.getId());
            if (CollectionUtils.isEmpty(children)) {
                lawTreeNodeVO.setChildren(lawDFS(specialtyId, lawTypePO.getId()));
            } else {
                lawTreeNodeVO.setChildren(children);
            }

            lawTreeNodeVOs.add(lawTreeNodeVO);
        }
        return lawTreeNodeVOs;
    }

    private List<LawTreeNodeVO> lawDFS(String specialtyId, String typeId) throws Exception {
        List<LawTreeNodeVO> lawTreeNodeVOs = new ArrayList<>();
        for (LawPO lawPO: lawService.getLaws(specialtyId, typeId)) {
            LawTreeNodeVO lawTreeNodeVO = new LawTreeNodeVO();
            lawTreeNodeVO.setTitle(lawPO.getCheckContent());
            lawTreeNodeVO.setKey(lawPO.getId());
            lawTreeNodeVOs.add(lawTreeNodeVO);
        }
        return lawTreeNodeVOs;
    }
}
