package com.github.actic.extension.test.customer.client;

import java.util.List;

import com.github.acticfox.common.api.result.ResultDTO;

/**
 * CustomerServiceI
 *
 * @author fanyong.kfy 2018-01-06 7:24 PM
 */
public interface NoticeService {
    public ResultDTO<?> notice(List<Long> list);

}
