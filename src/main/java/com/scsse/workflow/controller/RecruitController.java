package com.scsse.workflow.controller;

import com.scsse.workflow.constant.PredicateType;
import com.scsse.workflow.entity.dto.RecruitDto;
import com.scsse.workflow.entity.model.Recruit;
import com.scsse.workflow.service.RecruitService;
import com.scsse.workflow.service.UserService;
import com.scsse.workflow.util.MVCUtil.QueryParameterBuilder;
import com.scsse.workflow.util.Result.Result;
import com.scsse.workflow.util.Result.ResultUtil;
import com.scsse.workflow.util.DAOUtil.UserUtil;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * @author Andrew Dong
 * @ProjectName workflow
 * @date 2019-09-14 15:49
 */
@RestController
public class RecruitController {

    private final UserUtil userUtil;

    private final RecruitService recruitService;
    private final UserService userService;

    @Autowired
    public RecruitController(UserUtil userUtil, RecruitService recruitService, UserService userService) {
        this.userUtil = userUtil;
        this.recruitService = recruitService;
        this.userService = userService;
    }


    /**
     * 分页返回指定时间前(防止新数据写入到数据库时出现bug)的应聘数据
     *
     * @param recruitName     应聘名称(可选, 模糊查询)
     * @param recruitPosition 职位(可选, 严格匹配)
     * @param pageNum         页码，从0开始(可选,默认0)
     * @param pageSize        每页条数(可选,默认10)
     * @param currentTime     指定时间点 @DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ss")
     * @return List{RecruitListDto}
     * 例:
     * url:
     * /recruit/all?recruitPosition=java_backend&&time=xxx&&offset=1  xxx时间点前第2页的职位为后端的应聘数据
     * /recruit/all?recruitName=java?recruitPosition=java_backend&&time=xxx&&offset=0 xxx时间点前第1页的职位为后端的recruitName带java的应聘数据
     * @see RecruitDto 返回详细属性见此类
     */
    @GetMapping("/recruit/all")
    public Result getRecruitList(@RequestParam(required = false) String recruitName,
                                 @RequestParam(required = false) String recruitPosition,
                                 @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                 @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam String currentTime) {
        HashMap<Integer, Pair<String, String>> requestParam = new QueryParameterBuilder()
                .addParameter(PredicateType.LIKE, "recruitName", recruitName)
                .addParameter(PredicateType.EQUAL, "recruitPosition", recruitPosition)
                .addParameter(PredicateType.TIME_COMPARE, "createTime", currentTime)
                .build();

        return ResultUtil.success(recruitService.findPaginationRecruitWithCriteria(pageNum, pageSize, requestParam));
    }

    /**
     * 某条招聘的具体信息
     *
     * @param recruitId recruitId
     * @return RecruitDto
     * 例:
     * url:
     * /recruit/1
     * @see RecruitDto 返回详细属性见此类
     */
    @GetMapping("/recruit/{recruitId}")
    public Result getRecruitDetail(@PathVariable() Integer recruitId) {
        return ResultUtil.success(recruitService.findRecruitById(recruitId));
    }


    /**
     * 获取<b>调用者</b>申请应聘的所有应聘
     *
     * @return RecruitDto
     * @see RecruitDto
     */
    @GetMapping("/recruit/applied")
    public Result getAppliedRecruit() {
        return ResultUtil.success(
                userService.findAllRegisteredRecruit(
                        userUtil.findLoginUserId()
                )
        );
    }

    /**
     * 获取<b>调用者</b>成功加入应聘的所有应聘
     *
     * @return RecruitDto
     * @see RecruitDto
     */
    @GetMapping("/recruit/assigned")
    public Result getAssignedRecruit() {
        return ResultUtil.success(
                userService.findAllAssignedRecruit(
                        userUtil.findLoginUserId()
                )
        );
    }

    /**
     * 创建一条应聘
     *
     * @param recruit 招聘
     * @return RecruitListDto
     * 例:
     * url:
     * /recruit
     * @see RecruitDto
     */
    @PostMapping("/recruit")
    public Result createOneRecruit(@RequestBody() Recruit recruit) {
        return ResultUtil.success(recruitService.createRecruit(recruit));
    }

    @PutMapping("/recruit/{recruitId}")
    public Result updateOneRecruit(@RequestBody() Recruit recruit, @PathVariable Integer recruitId) {
        recruit.setRecruitId(recruitId);
        return ResultUtil.success(recruitService.updateRecruit(recruit));
    }

    @DeleteMapping("/recruit/{recruitId}")
    public Result deleteOneRecruit(@PathVariable Integer recruitId) {
        recruitService.deleteRecruitById(recruitId);
        return ResultUtil.success();
    }


}
