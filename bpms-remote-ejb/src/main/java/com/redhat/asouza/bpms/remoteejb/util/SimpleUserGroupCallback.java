package com.redhat.asouza.bpms.remoteejb.util;

import java.util.List;

import org.kie.api.task.UserGroupCallback;

/**
 * A super permissive UserGroupCallback.
 * 
 * @author <a href="mailto:asouza@redhat.com">Ângelo Galvão</a>
 *
 */
public class SimpleUserGroupCallback implements UserGroupCallback {

	@Override
	public boolean existsUser(String userId) {
		return true;
	}

	@Override
	public boolean existsGroup(String groupId) {
		return true;
	}

	@Override
	public List<String> getGroupsForUser(String userId, List<String> groupIds, List<String> allExistingGroupIds) {
		return allExistingGroupIds;
	}

}
