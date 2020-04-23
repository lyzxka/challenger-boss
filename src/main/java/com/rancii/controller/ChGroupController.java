package com.rancii.controller;

import com.rancii.entity.VO.GroupVO;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChGroup;
import com.rancii.service.ChGroupService;
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
 * 队伍  前端控制器
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Controller
@RequestMapping("/chGroup")
public class ChGroupController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChGroupController.class);

    @Autowired
    private ChGroupService chGroupService;

    @GetMapping("list")
    @SysLog("跳转队伍列表")
    public String list(String groupNo,Model model){
        model.addAttribute("groupNo",groupNo);
        return "/chGroup/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求队伍列表数据")
    public LayerData<GroupVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<GroupVO> layerData = new LayerData<>();
        IPage<GroupVO> pageData = chGroupService.selectGroupForPage(map,new Page(page,limit));
        layerData.setData(pageData.getRecords());
        layerData.setCount((int)pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增队伍页面")
    public String add(){
        return "/chGroup/add";
    }

    @PostMapping("add")
    @SysLog("保存新增队伍数据")
    @ResponseBody
    public RestResponse add(ChGroup chGroup){
        chGroupService.save(chGroup);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑队伍页面")
    public String edit(Long id,Model model){
        ChGroup chGroup = chGroupService.getById(id);
        model.addAttribute("chGroup",chGroup);
        return "/chGroup/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑队伍数据")
    public RestResponse edit(ChGroup chGroup){
        if(null == chGroup.getId() || 0 == chGroup.getId()){
            return RestResponse.failure("ID不能为空");
        }
        chGroupService.updateById(chGroup);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除队伍数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ChGroup chGroup = chGroupService.getById(id);
        chGroup.setDelFlag(true);
        chGroupService.updateById(chGroup);
        return RestResponse.success();
    }
    @PostMapping("audit")
    @ResponseBody
    @SysLog("审核组队请求")
    public RestResponse audit(@RequestParam(value = "id",required = false)Long id,@RequestParam(value = "status",required = false)String status){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        if(!"2".equals(status)&&!"3".equals(status)){
            return RestResponse.failure("审核状态不正确");
        }
        ChGroup chGroup = chGroupService.getById(id);
        if("2".equals(status)||"3".equals(status)){
            return RestResponse.failure("不能重复审核");
        }
        chGroup.setStatus(status);
        chGroupService.updateById(chGroup);
        return RestResponse.success();
    }
}