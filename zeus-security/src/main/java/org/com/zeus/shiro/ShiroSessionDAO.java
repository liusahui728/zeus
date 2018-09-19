package org.com.zeus.shiro;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.crazycake.shiro.RedisSessionDAO;
import org.crazycake.shiro.SerializeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class ShiroSessionDAO extends AbstractSessionDAO {
	@Autowired
	JedisCluster jedisCluster;

	private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

	/**
	 * The Redis key prefix for the sessions
	 */
	private String keyPrefix = "shiro_redis_session:";

	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}

	/**
	 * save session
	 * 
	 * @param session
	 * @throws UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}

		byte[] key = getByteKey(session.getId());
		byte[] value = SerializeUtils.serialize(session);
		jedisCluster.set(key, value);
	}

	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session or session id is null");
			return;
		}
		jedisCluster.del(this.getByteKey(session.getId()));

	}

	@Override
	public Collection<Session> getActiveSessions() {
		return keys(this.keyPrefix + "*");
	}

	public Set<Session> keys(String pattern) {
		Set<Session> sessions = new HashSet<>();
		Map<String, JedisPool> clusterNodes = jedisCluster.getClusterNodes();
		for (String k : clusterNodes.keySet()) {
			JedisPool jp = clusterNodes.get(k);
			try (Jedis connection = jp.getResource()) {
				Set<String> sets = connection.keys(pattern);
				if (sets != null && sets.size() > 0) {
					for (String key : sets) {
						System.out.println("key:"+key);
						Session s = (Session) SerializeUtils.deserialize(jedisCluster.get(key.getBytes()));
						sessions.add(s);
					}
				}
			} catch (Exception e) {
				logger.error("Getting keys error: {}", e);
			}
		}
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			logger.error("session id is null");
			return null;
		}

		Session s = (Session) SerializeUtils.deserialize(jedisCluster.get(this.getByteKey(sessionId)));
		return s;
	}

	/**
	 * 鑾峰緱byte[]鍨嬬殑key
	 * 
	 * @param key
	 * @return
	 */
	private byte[] getByteKey(Serializable sessionId) {
		String preKey = this.keyPrefix + sessionId;
		return preKey.getBytes();
	}

	/**
	 * Returns the Redis session keys prefix.
	 * 
	 * @return The prefix
	 */
	public String getKeyPrefix() {
		return keyPrefix;
	}

	/**
	 * Sets the Redis sessions key prefix.
	 * 
	 * @param keyPrefix The prefix
	 */
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	
}
