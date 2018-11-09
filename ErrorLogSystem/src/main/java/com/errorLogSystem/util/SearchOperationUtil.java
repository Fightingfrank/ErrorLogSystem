package com.errorLogSystem.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.errorLogSystem.model.ErrorListModel;
import com.errorLogSystem.model.ErrorObject;

@Aspect
@Component
public class SearchOperationUtil {

    private static Logger logger = LoggerFactory.getLogger(SearchOperationUtil.class);

    // private Map<String, List<ErrorObject>> newErrorMap = new HashMap<String,
    // List<ErrorObject>>();
    private int sortFlag = 0;

    @Autowired
    private ErrorListModel errorListModel;

    @Autowired
    private FileUtil fileUtil;

    // 临时
    @Autowired
    private RedisUtil redisUtil;

    public List<ErrorObject> getTopNErrorList(int n) {
        if (sortFlag == 0) {
            sortList(1);
            sortFlag = 1;
        }
        if (errorListModel.getErrorList().size() < n) {
            logger.info("error不足 " + n + "条 ： size = " + errorListModel.getErrorList().size());
            return errorListModel.getErrorList();
        }
        return errorListModel.getErrorList().subList(0, n);
    }

    public ErrorObject getErrorByKey(String key) {
        for (ErrorObject object : getAllError()) {
            if (object.getKey().equalsIgnoreCase(key))
                return object;
        }
        logger.error("没有查询到" + key);
        return null;
    }

    /**
     * 获取所有的error，如果errorListmodel中有数据，则直接从errorListModel中读取，否则先从redis拿数据，
     * 再放入到errorListModel中去
     * 
     * @return
     */
    public List<ErrorObject> getAllError() {
        // if(erro)
        return errorListModel.getErrorList();
        // 还没加载数据
    }

    public void initErrorListModel() {
        errorListModel = (ErrorListModel) fileUtil.readFileToBean();
        if (null == errorListModel) {
            // 从redis初始化数据
            logger.info("未找到历史文件数据，从redis读取初始数据");
            List<ErrorObject> errorList = new ArrayList<ErrorObject>();
            Jedis jedis = redisUtil.getJedisConnection();
            Set<String> sets = jedis.keys("URLERROR*");
            for (String str : sets) {
                Map<String, String> keys = jedis.hgetAll(str);
                for (Map.Entry<String, String> entry : keys.entrySet()) {
                    ErrorObject object = new ErrorObject();
                    object.setHashUrl(str);
                    object.setKey(entry.getKey());
                    object.setNum(entry.getValue());
                    object.setPriority("0"); // 都是同样的最低优先级
                    errorList.add(object);
                }
            }
            errorListModel = new ErrorListModel();
            // 没有数据，放入数据
            errorListModel.setErrorList(errorList);
            // 释放redis连接资源
            jedis.close();
            // 把当前数据写入到文件，下次直接从文件读取
            fileUtil.writeBeanToFile(errorListModel);
        }
        // 对数据进行排序
        sortList(1);
        sortFlag = 1;
    }

    // public void check
    /**
     * 获取最后几个error
     * 
     * @param n
     * @return
     */
    public List<ErrorObject> getLastNError(int n) {
        if (sortFlag == 0) {
            sortList(1);
            sortFlag = 1;
        }
        List<ErrorObject> lists = errorListModel.getErrorList();
        if (errorListModel.getErrorList().size() < n) {
            logger.info("error不足 " + n + "条 ： size = " + lists.size());
            return lists;
        }
        return lists.subList(lists.size() - n, lists.size());
    }

    /**
     * 获取出现的新的error
     * 
     * @return
     */
    public List<ErrorObject> getNewError() {
        List<ErrorObject> lists = errorListModel.getNewErrorList();
        if (null != lists && lists.size() != 0) {
            return lists;
        } else {
            logger.error("查询不到新增的error");
        }
        return null;
    }

    public void sortList(int flag) {
        errorListModel.setErrorList(ListComparator.sortList(errorListModel.getErrorList(), flag));
    }
}
