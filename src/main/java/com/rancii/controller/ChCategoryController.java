package com.rancii.controller;

import com.xiaoleilu.hutool.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rancii.entity.ChCategory;
import com.rancii.service.ChCategoryService;
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
 * 门类  前端控制器
 * </p>
 *
 * @author hantw
 * @since 2020-02-23
 */
@Controller
@RequestMapping("/chCategory")
public class ChCategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChCategoryController.class);

    @Autowired
    private ChCategoryService chCategoryService;

    @GetMapping("list")
    @SysLog("跳转门类列表")
    public String list(){
        return "/chCategory/list";
    }

    @PostMapping("list")
    @ResponseBody
    @SysLog("请求门类列表数据")
    public LayerData<ChCategory> list(@RequestParam(value = "page",defaultValue = "1")Integer page,
                                      @RequestParam(value = "limit",defaultValue = "10")Integer limit,
                                      ServletRequest request){
        Map map = WebUtils.getParametersStartingWith(request, "s_");
        LayerData<ChCategory> layerData = new LayerData<>();
        QueryWrapper<ChCategory> wrapper = new QueryWrapper<>();
        wrapper.eq("del_flag",false);
        if(!map.isEmpty()){
            String categoryName = (String) map.get("categoryName");
            if(StringUtils.isNotBlank(categoryName)) {
                wrapper.like("category_name",categoryName);
            }else{
                map.remove("categoryName");
            }

            String categoryNo = (String) map.get("categoryNo");
            if(StringUtils.isNotBlank(categoryNo)) {
                wrapper.like("category_no",categoryNo);
            }else{
                map.remove("categoryNo");
            }

        }
        IPage<ChCategory> pageData = chCategoryService.page(new Page<>(page,limit),wrapper);
        layerData.setData(pageData.getRecords());
        layerData.setCount((int)pageData.getTotal());
        return layerData;
    }

    @GetMapping("add")
    @SysLog("跳转新增门类页面")
    public String add(){
        return "/chCategory/add";
    }

    @PostMapping("add")
    @SysLog("保存新增门类数据")
    @ResponseBody
    public RestResponse add(ChCategory chCategory){
        chCategoryService.save(chCategory);
        return RestResponse.success();
    }

    @GetMapping("edit")
    @SysLog("跳转编辑门类页面")
    public String edit(Long id,Model model){
        ChCategory chCategory = chCategoryService.getById(id);
        model.addAttribute("chCategory",chCategory);
        return "/chCategory/edit";
    }

    @PostMapping("edit")
    @ResponseBody
    @SysLog("保存编辑门类数据")
    public RestResponse edit(ChCategory chCategory){
        if(null == chCategory.getId() || 0 == chCategory.getId()){
            return RestResponse.failure("ID不能为空");
        }
        chCategoryService.updateById(chCategory);
        return RestResponse.success();
    }

    @PostMapping("delete")
    @ResponseBody
    @SysLog("删除门类数据")
    public RestResponse delete(@RequestParam(value = "id",required = false)Long id){
        if(null == id || 0 == id){
            return RestResponse.failure("ID不能为空");
        }
        ChCategory chCategory = chCategoryService.getById(id);
        chCategory.setDelFlag(true);
        chCategoryService.updateById(chCategory);
        return RestResponse.success();
    }

}