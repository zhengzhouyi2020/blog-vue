package com.zzy.controller;

import com.zzy.utils.constant.CommonConstant;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 七牛云controller层
 * @Author Zzy
 * @Date 2021/1/19
 */
@RestController
@RequestMapping(CommonConstant.BASE_API+"/qiniu")
@Api(value = "QiNiuController", tags = {"七牛云接口"})
public class QiniuController {
}
