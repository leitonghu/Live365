package com.tigger.live365.bean;

/**
 * 推流实例
 * Created by leitonghu on 17/2/6.
 */
public class PushBean {

    private String id;
    private String hub;
    private String title;
    private String publishKey;
    private String publishSecurity;

    private HostBean hosts;


    public PushBean() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHub() {
        return hub;
    }

    public void setHub(String hub) {
        this.hub = hub;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishKey() {
        return publishKey;
    }

    public void setPublishKey(String publishKey) {
        this.publishKey = publishKey;
    }

    public String getPublishSecurity() {
        return publishSecurity;
    }

    public void setPublishSecurity(String publishSecurity) {
        this.publishSecurity = publishSecurity;
    }

    public HostBean getHostBean() {
        return hosts;
    }

    public void setHostBean(HostBean hostBean) {
        this.hosts = hostBean;
    }


    public class HostBean {

        private String hosts;
        private PublishBean publish;


        public PublishBean getPublishBean() {
            return publish;
        }

        public void setPublishBean(PublishBean publishBean) {
            this.publish = publishBean;
        }

        public String getHosts() {
            return hosts;
        }

        public void setHosts(String hosts) {
            this.hosts = hosts;
        }

        public class PublishBean {

            private String rtmp;

            public String getRtmp() {
                return rtmp;
            }

            public void setRtmp(String rtmp) {
                this.rtmp = rtmp;
            }
        }
    }


}
