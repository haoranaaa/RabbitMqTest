package com.guman.controller;

import javax.annotation.Resource;
import javax.xml.ws.Response;
import java.util.logging.Handler;

/**
 * @author duanhaoran
 * @since 2019/7/24 7:21 PM
 */
public abstract class AbstractEventController {



    public abstract Handler getHandler();

}
