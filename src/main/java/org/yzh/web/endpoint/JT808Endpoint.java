package org.yzh.web.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.yzh.framework.mvc.annotation.Endpoint;
import org.yzh.framework.mvc.annotation.Mapping;
import org.yzh.framework.orm.model.AbstractMessage;
import org.yzh.framework.session.MessageManager;
import org.yzh.framework.session.Session;
import org.yzh.framework.session.SessionManager;
import org.yzh.web.jt.basics.Header;
import org.yzh.web.jt.t808.*;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.yzh.web.jt.common.JT808.*;

@Endpoint
@Component
public class JT808Endpoint {

    private static final Logger log = LoggerFactory.getLogger(JT808Endpoint.class.getSimpleName());

    private MessageManager messageManager = MessageManager.Instance;

    private SessionManager sessionManager = SessionManager.getInstance();

    @Mapping(types = 终端通用应答, desc = "终端通用应答")
    public void 终端通用应答(T0001 message) {
        Header header = message.getHeader();
        String mobileNo = header.getMobileNo();
        Integer replyId = message.getReplyId();
        messageManager.response(message);
    }

    @Mapping(types = 查询终端参数应答, desc = "查询终端参数应答")
    public void 查询终端参数应答(T0104 message) {
        Header header = message.getHeader();
        String mobileNo = header.getMobileNo();
        Integer replyId = header.getSerialNo();
        messageManager.response(message);
    }

    @Mapping(types = 查询终端属性应答, desc = "查询终端属性应答")
    public void 查询终端属性应答(T0107 message) {
        Header header = message.getHeader();
        String mobileNo = header.getMobileNo();
        messageManager.response(message);
    }

    @Mapping(types = {位置信息查询应答, 车辆控制应答}, desc = "位置信息查询应答/车辆控制应答")
    public void 位置信息查询应答(T0201_0500 message) {
        Header header = message.getHeader();
        String mobileNo = header.getMobileNo();
        Integer replyId = header.getSerialNo();
        messageManager.response(message);
    }

    @Mapping(types = 终端RSA公钥, desc = "终端RSA公钥")
    public void 终端RSA公钥(T0A00_8A00 message) {
        Header header = message.getHeader();
        String mobileNo = header.getMobileNo();
        messageManager.response(message);
    }

    @Mapping(types = 摄像头立即拍摄命令应答, desc = "摄像头立即拍摄命令应答")
    public void 摄像头立即拍摄命令应答(T0805 message) {
        Header header = message.getHeader();
        String mobileNo = header.getMobileNo();
        Integer replyId = header.getSerialNo();
        messageManager.response(message);
    }

    @Mapping(types = 存储多媒体数据检索应答, desc = "存储多媒体数据检索应答")
    public void 存储多媒体数据检索应答(T0802 message, Session session) {
        Header header = message.getHeader();
        String mobileNo = header.getMobileNo();
        Integer replyId = header.getSerialNo();
        messageManager.response(message);
    }
    //=============================================================

    @Mapping(types = 终端心跳, desc = "终端心跳")
    public T0001 heartBeat(Header header, Session session) {
        T0001 result = new T0001(终端心跳, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 补传分包请求, desc = "补传分包请求")
    public T0001 补传分包请求(T8003 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(补传分包请求, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 终端注册, desc = "终端注册")
    public T8100 register(T0100 message, Session session) {
        Header header = message.getHeader();
        //TODO
        session.setTerminalId(header.getTerminalId());
        sessionManager.put(Session.buildId(session.getChannel()), session);

        T8100 result = new T8100(header.getSerialNo(), T8100.Success, "test_token");
        result.setHeader(new Header(终端注册应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 终端注销, desc = "终端注销")
    public T0001 终端注销(AbstractMessage message, Session session) {
        Header header = (Header) message.getHeader();
        //TODO
        T0001 result = new T0001(终端注销, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 终端鉴权, desc = "终端鉴权")
    public T0001 authentication(T0102 message, Session session) {
        Header header = message.getHeader();
        //TODO
        session.setTerminalId(header.getMobileNo());
        sessionManager.put(Session.buildId(session.getChannel()), session);
        T0001 result = new T0001(终端鉴权, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 终端升级结果通知, desc = "终端升级结果通知")
    public T0001 终端升级结果通知(T0108 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(终端升级结果通知, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 位置信息汇报, desc = "位置信息汇报")
    public T0001 位置信息汇报(T0200 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(位置信息汇报, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 人工确认报警消息, desc = "人工确认报警消息")
    public T0001 人工确认报警消息(T8203 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(位置信息汇报, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 事件报告, desc = "事件报告")
    public T0001 事件报告(T0301 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(事件报告, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 提问应答, desc = "提问应答")
    public T0001 提问应答(T0302 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(提问应答, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 信息点播_取消, desc = "信息点播/取消")
    public T0001 信息点播取消(T0303 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(信息点播_取消, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 行驶记录仪数据上传, desc = "行驶记录仪数据上传")
    public T0001 行驶记录仪数据上传(AbstractMessage message, Session session) {
        Header header = (Header) message.getHeader();
        //TODO
        T0001 result = new T0001(行驶记录仪数据上传, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 电子运单上报, desc = "电子运单上报")
    public T0001 电子运单上报(AbstractMessage message, Session session) {
        Header header = (Header) message.getHeader();
        //TODO
        T0001 result = new T0001(电子运单上报, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 驾驶员身份信息采集上报, desc = "驾驶员身份信息采集上报")
    public T0001 驾驶员身份信息采集上报(T0702 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(驾驶员身份信息采集上报, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 定位数据批量上传, desc = "定位数据批量上传")
    public T0001 定位数据批量上传(T0704 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(定位数据批量上传, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = CAN总线数据上传, desc = "定位数据批量上传")
    public T0001 CAN总线数据上传(T0705 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(CAN总线数据上传, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 多媒体事件信息上传, desc = "多媒体事件信息上传")
    public T0001 多媒体事件信息上传(T0800 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(多媒体事件信息上传, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 多媒体数据上传, desc = "多媒体数据上传")
    public T8800 多媒体数据上传(T0801 message, Session session) throws IOException {
        Header header = message.getHeader();

        byte[] packet = message.getPacket();
        FileOutputStream fos = new FileOutputStream("D://test.jpg");
        fos.write(packet);
        fos.close();

        T8800 result = new T8800();
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        result.setMediaId(1);
        return result;
    }

    @Mapping(types = 数据上行透传, desc = "数据上行透传")
    public T0001 passthrough(T8900_0900 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(数据上行透传, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

    @Mapping(types = 数据压缩上报, desc = "数据压缩上报")
    public T0001 gzipPack(T0901 message, Session session) {
        Header header = message.getHeader();
        //TODO
        T0001 result = new T0001(数据压缩上报, header.getSerialNo(), T0001.Success);
        result.setHeader(new Header(平台通用应答, session.currentFlowId(), header.getMobileNo()));
        return result;
    }

}