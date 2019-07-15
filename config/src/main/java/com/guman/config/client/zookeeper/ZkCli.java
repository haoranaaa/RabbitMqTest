package com.guman.config.client.zookeeper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Lists;
import com.guman.common.json.JSON;
import com.guman.common.json.JsonException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author duanhaoran
 * @since 2019/6/4 9:00 PM
 */
public class ZkCli {

    private static final Logger logger = LoggerFactory.getLogger(ZkCli.class);

    private static ZooKeeper getZk() throws IOException {
        try {
            return new ZooKeeper("localhost:2181", 1000, (i)-> System.out.println("node change"));
        } catch (IOException e) {
            logger.error("get zk exception ! ", e);
            throw e;
        }
    }

    public static List<String> getList(String node) throws IOException {
        List<String> children = Lists.newArrayList();
        try {
            if (!StringUtils.hasText(node)) {
                return getZk().getChildren("/", false);
            } else {
                return getZk().getChildren(node, false);
            }
        } catch (KeeperException | InterruptedException e) {
            logger.error("connection zk error ! please to check your zk address ! ", e);
        }
        return children;
    }

    public static <T> T getData(String path, Class<T> clazz) throws IOException {

        try {
            return JSON.readValue(getDataString(path), clazz);
        } catch (KeeperException | InterruptedException e) {
            logger.error("get data error ! ", e);
        } catch (JsonException e) {
            logger.error("Json format error ! please check your Json is right! ");
        }
        return null;
    }
    public static <T> T getData(String path, TypeReference<T> typeReference) throws IOException {
        try {
            return JSON.readValue(getDataString(path), typeReference);
        } catch (KeeperException | InterruptedException e) {
            logger.error("get data error ! ", e);
        } catch (JsonException e) {
            logger.error("Json format error ! please check your Json is right! ");
        }
        return null;
    }

    private static String getDataString(String path) throws KeeperException, InterruptedException, IOException {
        byte[] data = getZk().getData(path, false, new Stat());
        return new String(data);
    }


    public static void main(String[] args) {
        try {
            System.out.println(getList(null));
            Thread.sleep(1000*20);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
