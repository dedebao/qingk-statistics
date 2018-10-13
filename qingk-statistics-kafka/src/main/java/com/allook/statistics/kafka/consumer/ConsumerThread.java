package com.allook.statistics.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.message.MessageAndMetadata;


/**
 * 消费者线程
 * 
 * @since v1.0.0
 * @author zhuguoxiao<br>
 *         Created on 2018年5月3日
 */
public abstract class ConsumerThread extends Thread {
	protected static final String LOGGER_FORMAT = "[{}], {}";
	protected static final String STRING_FORMAT = "[%s], %s";
	protected static final Logger LOGGER = LoggerFactory.getLogger(ConsumerThread.class);

	protected enum LogLevel {
		DEBUG, INFO, WARN, ERROR;
	}

	/** kafka消息流 */
	private KafkaStream<byte[], byte[]> stream;

	public ConsumerThread(KafkaStream<byte[], byte[]> stream, String groupId, String topic, int threadNumber) {
		setName(String.format("consumer-thread-%s-%s-%d", groupId, topic, threadNumber));
		this.stream = stream;
	}

	public void run() {
		LOGGER.info(LOGGER_FORMAT, getName(), "已启动.");

		// 获取消息，无信息时状态为block
		ConsumerIterator<byte[], byte[]> iter = stream.iterator();
		while (iter.hasNext()) {
			MessageAndMetadata<byte[], byte[]> msg = iter.next();
			String message = new String(msg.message());

			LOGGER.debug(LOGGER_FORMAT, getName(), "收到消息：" + message);

			try {
				execute(message);
			} catch (Exception e) {
				LOGGER.error(String.format("[%s], %s 错误数据位置：topic=%s, partition=%d, offset=%d", //
						getName(), "未知异常.", msg.topic(), msg.partition(), msg.offset()), e);
			}
		}
	}

	public abstract void execute(String message) throws Exception;

	/**
	 * 打印日志
	 * 
	 * @param level   日志级别
	 * @param content 日志内容
	 * @since v1.0.0
	 * @author zhuguoxiao<br>
	 *         Created on 2018年6月6日
	 */
	protected void log(LogLevel level, String content) {
		switch (level) {
		case DEBUG:
			LOGGER.debug(LOGGER_FORMAT, getName(), content);
			break;

		case INFO:
			LOGGER.info(LOGGER_FORMAT, getName(), content);
			break;

		case WARN:
			LOGGER.warn(LOGGER_FORMAT, getName(), content);
			break;

		case ERROR:
			LOGGER.error(LOGGER_FORMAT, getName(), content);
			break;

		default:
			// do nothing.
			break;
		}
	}

	/**
	 * 打印日志
	 * 
	 * @param level   日志级别
	 * @param content 日志内容
	 * @param t       异常
	 * @since v1.0.0
	 * @author zhuguoxiao<br>
	 *         Created on 2018年6月6日
	 */
	protected void log(LogLevel level, String content, Throwable t) {
		switch (level) {
		case DEBUG:
			LOGGER.debug(String.format(STRING_FORMAT, getName(), content), t);
			break;

		case INFO:
			LOGGER.info(String.format(STRING_FORMAT, getName(), content), t);
			break;

		case WARN:
			LOGGER.warn(String.format(STRING_FORMAT, getName(), content), t);
			break;

		case ERROR:
			LOGGER.error(String.format(STRING_FORMAT, getName(), content), t);
			break;

		default:
			// do nothing.
			break;
		}
	}

}
