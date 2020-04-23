package com.rancii.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rancii.entity.ChCategory;
import com.rancii.entity.VO.GroupSearchVO;
import com.rancii.service.ChCategoryService;
import com.rancii.service.ChGroupService;
import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChGroupSearch;
import com.rancii.service.ChGroupSearchService;
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
 * 组队请求  前端控制器
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Controller
@RequestMapping("/chGroupSearch")
public class ChGroupSearchController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChGroupSearchController.class);

    @Autowired
    private ChGroupSearchService chGroupSearchService;
    @Autowired
    private ChGroupService groupService;
    @Autowired
    private ChCategoryService categoryService;


    @GetMapping("list")
    @SysLog("跳转组队请求列表")
    public String list(Model model){
        List<ChCategory> list=categoryService.list(new LambdaQueryWrapper<ChCategory>().select(ChCategory::getCategoryName,ChCategory::getId).eq(ChCategory::getDelFlag,false));
        model.addAttribute("categoryList",list);
        return "/chGroupSearch/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求组队请求列表数据")
    public LayerData<GroupSearchVO> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<GroupSearchVO> layerData = new LayerData<>();
        IPage<GroupSearchVO> pageData = chGroupSearchService.selectGroupSearchForPage(map,new Page<>(page,limit));
        layerData.setData(pageData.getRecords());
        layerData.setCount((int)pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增组队请求页面")
    public String add(){

        return "/chGroupSearch/add";
    }

    @PostMapping("add")
    @SysLog("保存新增组队请求数据")
    @ResponseBody
    public RestResponse add(ChGroupSearch chGroupSearch){
        chGroupSearchService.save(chGroupSearch);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑组队请求页面")
    public String edit(Long id,Model model){
        ChGroupSearch chGroupSearch = chGroupSearchService.getById(id);
        model.addAttribute("chGroupSearch",chGroupSearch);
        return "/chGroupSearch/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑组队请求数据")
    public RestResponse edit(ChGroupSearch chGroupSearch){
        if(null == chGroupSearch.getId() || 0 == chGroupSearch.getId()){
            return RestResponse.failure("ID不能为空");
        }
        chGroupSearchService.updateById(chGroupSearch);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除组队请求数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ChGroupSearch chGroupSearch = chGroupSearchService.getById(id);
        chGroupSearch.setDelFlag(true);
        chGroupSearchService.updateById(chGroupSearch);
        return RestResponse.success();
    }

}