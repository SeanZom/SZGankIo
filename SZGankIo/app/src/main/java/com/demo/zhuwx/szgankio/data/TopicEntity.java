package com.demo.zhuwx.szgankio.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sean Zhu
 *         Email : seanzhuwx@gmail.com
 *         Date : 2016/11/24
 *         Description :
 */

public class TopicEntity {


    /**
     * _id : 5833c3b3421aa926e43aef90
     * createdAt : 2016-11-22T12:04:03.555Z
     * desc : 随着 Android 引入 Java 8 的一些功能，请记住每一个标准库的 API 和语言特性都会带来一些相关的开销，这很重要。虽然设备越来越快而且内存越来越多，代码大小和性能优化之间仍然是有着紧密关联的。
     * images : ["http://img.gank.io/b530a4e3-9ec8-4166-8c8f-fdd29e11c0d5","http://img.gank.io/8b3cf104-4b27-4dbd-8407-769d622ca077"]
     * publishedAt : 2016-11-23T11:27:52.847Z
     * source : web
     * type : Android
     * url : https://realm.io/cn/news/360andev-jake-wharton-java-hidden-costs-android/
     * used : true
     * who : Chen Mulong
     */

    @SerializedName("_id")
    private String topicId;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String id) {
        this.topicId = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt.split("T")[0];
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images == null ? new ArrayList<String>()  : images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
