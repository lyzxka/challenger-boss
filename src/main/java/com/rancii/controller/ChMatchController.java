package com.rancii.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rancii.entity.ChCategory;
import com.rancii.entity.VO.MatchVO;
import com.rancii.service.ChCategoryService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChMatch;
import com.rancii.service.ChMatchService;
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
 * 比赛  前端控制器
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Controller
@RequestMapping("/chMatch")
public class ChMatchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChMatchController.class);

    @Autowired
    private ChMatchService chMatchService;
    @Autowired
    private ChCategoryService categoryService;

    @GetMapping("list")
    @SysLog("跳转比赛列表")
    public String list(Model model){
        model.addAttribute("categoryList",categoryService.list(new LambdaQueryWrapper<ChCategory>().select(ChCategory::getCategoryName,ChCategory::getId).eq(ChCategory::getDelFlag,false)));
        return "/chMatch/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求比赛列表数据")
    public LayerData<MatchVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<MatchVO> layerData = new LayerData<>();
        IPage<MatchVO> pageData = chMatchService.selectMatchForPage(map,new Page<>(page,limit));
        layerData.setData(pageData.getRecords());
        layerData.setCount((int)pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增比赛页面")
    public String add(Model model){
        model.addAttribute("categoryList",categoryService.list(new LambdaQueryWrapper<ChCategory>().select(ChCategory::getCategoryName,ChCategory::getId).eq(ChCategory::getDelFlag,false)));
        return "/chMatch/add";
    }

    @PostMapping("add")
    @SysLog("保存新增比赛数据")
    @ResponseBody
    public RestResponse add(ChMatch chMatch){
        chMatchService.save(chMatch);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑比赛页面")
    public String edit(Long id,Model model){
        model.addAttribute("categoryList",categoryService.list(new LambdaQueryWrapper<ChCategory>().select(ChCategory::getCategoryName,ChCategory::getId).eq(ChCategory::getDelFlag,false)));
        ChMatch chMatch = chMatchService.getById(id);
        model.addAttribute("chMatch",chMatch);
        return "/chMatch/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑比赛数据")
    public RestResponse edit(ChMatch chMatch){
        if(null == chMatch.getId() || 0 == chMatch.getId()){
            return RestResponse.failure("ID不能为空");
        }
        chMatchService.updateById(chMatch);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除比赛数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ChMatch chMatch = chMatchService.getById(id);
        chMatch.setDelFlag(true);
        chMatchService.updateById(chMatch);
        return RestResponse.success();
    }

}