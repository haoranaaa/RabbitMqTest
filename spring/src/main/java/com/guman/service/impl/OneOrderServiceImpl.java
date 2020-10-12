package com.guman.service.impl;

import com.guman.service.OrderService;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author duanhaoran
 * @since 2019/10/14 1:13 PM
 */
@Service
@Order(1)
public class OneOrderServiceImpl implements OrderService {
    @Override
    public int getOrder() {
        return 1;
    }
}
