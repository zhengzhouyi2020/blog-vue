package com.zzy.controller;

import com.zzy.entity.SysComment;
import com.zzy.service.CommentService;
import com.zzy.utils.annotation.Log;
import com.zzy.utils.constant.CommonConstant;
import com.zzy.utils.controller.BaseController;
import com.zzy.utils.exception.GlobalException;
import com.zzy.utils.util.*;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description 评论controller层
 * @Author Zzy
 * @Date 2021/1/19
 */
@RestController
@RequestMapping(CommonConstant.BASE_API+"/comment")
@Api(value = "CommentController", tags = {"评论功能接口"})
public class CommentController extends BaseController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/findByArticle/{id}")
    public Result findByArticleId(@PathVariable("id") Long id) {
        return new Result<>(commentService.findByArticleId(id));
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable("id") Long id) {
        return new Result<>(commentService.getById(id));
    }

    @PostMapping("/list")
    public Result list(@RequestBody SysComment sysComment, QueryPage queryPage) {
        return new Result<>(super.getData(commentService.list(sysComment, queryPage)));

    }

    @PostMapping
    public Result save(@RequestBody SysComment sysComment, HttpServletRequest request) {
        try {
            String ip = IPUtil.getIpAddr(request);
            sysComment.setCreateTime(new Date());
            sysComment.setIp(ip);
            sysComment.setAddress(AddressUtil.getAddress(ip));
            String header=request.getHeader(CommonConstant.USER_AGENT);
            UserAgent userAgent= UserAgent.parseUserAgentString(header);
            Browser browser=userAgent.getBrowser();
            OperatingSystem operatingSystem=userAgent.getOperatingSystem();
            sysComment.setDevice(browser.getName()+","+operatingSystem.getName());
            commentService.add(sysComment);
            return new Result();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Log("删除评论")
    public Result delete(@PathVariable("id") Long id) {
        try {
            commentService.delete(id);
            return new Result();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @GetMapping("/chart")
    public Result chart() {
        return new Result<>(commentService.chart());
    }
}
