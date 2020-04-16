package com.xgs.hisystem.controller;

import com.xgs.hisystem.pojo.bo.PageRspBO;
import com.xgs.hisystem.pojo.bo.ValidationResultBO;
import com.xgs.hisystem.pojo.vo.*;
import com.xgs.hisystem.service.IAdminService;
import com.xgs.hisystem.util.ParamsValidationUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xgs
 * @date 2019/4/3
 * @description:
 */
@RestController
@RequestMapping(value = "/admin")
@Api(tags = "管理员操作API")
public class AdminController {

    @Autowired
    private IAdminService iadminService;

    /**
     * 新建角色
     *
     * @param roleVO
     * @return
     */
    @RequestMapping(value = "/createRole", method = RequestMethod.POST)
    public String createRole(@RequestBody RoleVO roleVO) {
        ValidationResultBO validateBo = ParamsValidationUtils.validateEntity(roleVO);
        if (validateBo.isHasErrors()) {
            return validateBo.getErrorMsg().values().toString();
        }
        BaseResponse baseResponse = iadminService.createRole(roleVO);
        return baseResponse.getMessage();

    }

    /**
     * 后台添加账户
     *
     * @param reqVO
     * @return
     */
    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String saveUserAndSendEmailTemp(@RequestBody UserRegisterReqVO reqVO) {
        ValidationResultBO validateBo = ParamsValidationUtils.validateEntity(reqVO);
        if (validateBo.isHasErrors()) {
            return validateBo.getErrorMsg().values().toString();
        }
        BaseResponse baseResponse = iadminService.saveUserAndSendEmailTemp(reqVO);
        return baseResponse.getMessage();
    }

    /**
     * 后台添加角色
     *
     * @param addRoleVO
     * @return
     */
    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    public BaseResponse addRole(@RequestBody AddRoleVO addRoleVO) {

        ValidationResultBO validateBo = ParamsValidationUtils.validateEntity(addRoleVO);
        if (validateBo.isHasErrors()) {
            return BaseResponse.errormsg(validateBo.getErrorMsg().toString());
        }
        return iadminService.addRole(addRoleVO);
    }


    /**
     * 获取审核角色
     *
     * @param
     * @return
     */
    @GetMapping(value = "/getRoleApply")
    public PageRspBO<applyRspVO> getRoleApply(BasePageReqVO reqVO) {


        return iadminService.getRoleApply(reqVO);

    }


    /**
     * 修改角色状态
     *
     * @param status
     * @param email
     */
    @PostMapping(value = "/changeRoleStatus")
    public void changeRoleStatus(@RequestParam String status, @RequestParam String email) {

        iadminService.changeRoleStatus(status, email);
    }

    /**
     * 公告
     *
     * @param reqVO
     * @return
     */
    @PostMapping(value = "/addAnnouncement")
    public String addAnnouncement(@RequestBody AnnouncementVO reqVO) {
        ValidationResultBO validateBo = ParamsValidationUtils.validateEntity(reqVO);
        if (validateBo.isHasErrors()) {
            return validateBo.getErrorMsg().values().toString();
        }
        BaseResponse baseResponse = iadminService.addAnnouncement(reqVO);
        return baseResponse.getMessage();
    }

    @GetMapping(value = "/getAnnouncement")
    public PageRspBO<AnnouncementVO> getAnnouncement(BasePageReqVO reqVO) {


        return iadminService.getAnnouncement(reqVO);
    }

    @PostMapping(value = "/changeAnnouncement")
    public String changeAnnouncement(@RequestBody AnnouncementVO announcementVO) {
        ValidationResultBO validateBo = ParamsValidationUtils.validateEntity(announcementVO);
        if (validateBo.isHasErrors()) {
            return validateBo.getErrorMsg().values().toString();
        }

        BaseResponse baseResponse = iadminService.changeAnnouncement(announcementVO);
        return baseResponse.getMessage();
    }

    @PostMapping(value = "/deleteAnnouncement")
    public String deleteAnnouncement(@RequestParam String id) {
        ValidationResultBO validateBo = ParamsValidationUtils.validateEntity(id);
        if (validateBo.isHasErrors()) {
            return validateBo.getErrorMsg().values().toString();
        }

        BaseResponse baseResponse = iadminService.deleteAnnouncement(id);

        return baseResponse.getMessage();
    }

    @PostMapping(value = "/showAnnouncement")
    public String showAnnouncement(@RequestParam String id) {
        ValidationResultBO validateBo = ParamsValidationUtils.validateEntity(id);
        if (validateBo.isHasErrors()) {
            return validateBo.getErrorMsg().values().toString();
        }

        BaseResponse baseResponse = iadminService.showAnnouncement(id);

        return baseResponse.getMessage();
    }

    @PostMapping(value = "/hiddenAnnouncement")
    public String hiddenAnnouncement(@RequestParam String id) {
        ValidationResultBO validateBo = ParamsValidationUtils.validateEntity(id);
        if (validateBo.isHasErrors()) {
            return validateBo.getErrorMsg().values().toString();
        }

        BaseResponse baseResponse = iadminService.hiddenAnnouncement(id);

        return baseResponse.getMessage();
    }

    @PostMapping(value = "/adddepartment")
    @ApiOperation(value = "添加科室", httpMethod = "POST", notes = "添加科室")
    @ApiImplicitParam(name = "reqVO",value = "添加科室", dataType = "AddDepartmentReqVO")
    public BaseResponse<?> addDepartment(@RequestBody AddDepartmentReqVO reqVO) {
        ValidationResultBO validateBo = ParamsValidationUtils.validateEntity(reqVO);
        if (validateBo.isHasErrors()) {
            return BaseResponse.errormsg(validateBo.getErrorMsg().values().toString());
        }
        return iadminService.addDepartment(reqVO);
    }

    @PostMapping(value = "/getDepartment")
    @ApiOperation(value = "获取所有科室", httpMethod = "POST", notes = "获取所有科室")
    public List<GetDepartmentRspVO> getDepartment() {
        return iadminService.getDepartment();
    }
}
