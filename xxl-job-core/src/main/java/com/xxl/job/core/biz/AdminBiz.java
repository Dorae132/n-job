package com.xxl.job.core.biz;

import com.xxl.job.core.biz.model.HandleCallbackParam;
import com.xxl.job.core.biz.model.RegistryParam;
import com.xxl.job.core.biz.model.ReturnT;

import java.util.List;

/**
 * @author xuxueli 2017-07-27 21:52:49
 */
public interface AdminBiz {

    public static final String MAPPING = "/api";


    // ---------------------- callback ----------------------

    /**
     * callback
     * 提供给执行器的回调
     *
     * @param callbackParamList
     * @return
     */
    public ReturnT<String> callback(List<HandleCallbackParam> callbackParamList);


    // ---------------------- registry ----------------------

    /**
     * registry
     * 执行器注册接口
     *
     * @param registryParam
     * @return
     */
    public ReturnT<String> registry(RegistryParam registryParam);

    /**
     * registry remove
     * 执行器下线接口
     *
     * @param registryParam
     * @return
     */
    public ReturnT<String> registryRemove(RegistryParam registryParam);


    // ---------------------- job opt ----------------------

    /**
     * trigger job for once
     * 调度任务
     *
     * @param jobId
     * @return
     */
    public ReturnT<String> triggerJob(int jobId);

}
