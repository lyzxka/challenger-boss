package com.rancii.controller;

import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChKnowledge;
import com.rancii.service.ChKnowledgeService;
import com.rancii.util.LayerData;
import com.rancii.util.RestResponse;
import com.rancii.annotation.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 知识资料  前端控制器
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Controller
@RequestMapping("/chKnowledge")
public class ChKnowledgeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChKnowledgeController.class);

    @Autowired
    private ChKnowledgeService chKnowledgeService;

    @GetMapping("list")
    @SysLog("跳转知识资料列表")
    public String list(){
        return "/chKnowledge/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求知识资料列表数据")
    public LayerData<ChKnowledge> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ChKnowledge> layerData = new LayerData<>();
        QueryWrapper<ChKnowledge> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String userId = (String) map.get("userId");
            if(StringUtils.isNotBlank(userId)) {
                wrapper.like("user_id",userId);
            }else{
                map.remove("userId");
            }

            String title = (String) map.get("title");
            if(StringUtils.isNotBlank(title)) {
                wrapper.like("title",title);
            }else{
                map.remove("title");
            }

            String categoryId = (String) map.get("categoryId");
            if(StringUtils.isNotBlank(categoryId)) {
                wrapper.like("category_id",categoryId);
            }else{
                map.remove("categoryId");
            }

        }
        IPage<ChKnowledge> pageData = chKnowledgeService.page(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount((int)pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增知识资料页面")
    public String add(){
        return "/chKnowledge/add";
    }

    @PostMapping("add")
    @SysLog("保存新增知识资料数据")
    @ResponseBody
    public RestResponse add(ChKnowledge chKnowledge){
        chKnowledgeService.save(chKnowledge);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑知识资料页面")
    public String edit(Long id,Model model){
        ChKnowledge chKnowledge = chKnowledgeService.getById(id);
        model.addAttribute("chKnowledge",chKnowledge);
        return "/chKnowledge/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑知识资料数据")
    public RestResponse edit(ChKnowledge chKnowledge){
        if(null == chKnowledge.getId() || 0 == chKnowledge.getId()){
            return RestResponse.failure("ID不能为空");
        }
        chKnowledgeService.updateById(chKnowledge);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除知识资料数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ChKnowledge chKnowledge = chKnowledgeService.getById(id);
        chKnowledge.setDelFlag(true);
        chKnowledgeService.updateById(chKnowledge);
        return RestResponse.success();
    }

}