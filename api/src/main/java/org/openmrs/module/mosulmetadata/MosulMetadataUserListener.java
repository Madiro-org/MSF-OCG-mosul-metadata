package org.openmrs.module.mosulmetadata;

import org.openmrs.Role;
import org.openmrs.User;
import org.openmrs.UserSessionListener;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of UserSessionListener hence adding roles to the corresponding
 * roles
 */
@Component
public class MosulMetadataUserListener implements UserSessionListener {

	private static final Logger log = LoggerFactory.getLogger(MosulMetadataUserListener.class);

    @Override
    public void loggedInOrOut(User user, Event event, Status status) {
        try {
            if (event == Event.LOGIN) {
                UserService userService = Context.getUserService();

                String mosulUsername = user.getUsername();
                
                String mosulRoleName = MosulMetadataConstants.USER_ROLES.get(mosulUsername);
                if (mosulRoleName != null) {
                    Role mosulRole = userService.getRole(mosulRoleName);
                    verifyUSerRole(user, mosulRole);
                } else {
                    log.info("user: '{}' is not a Mosul user hence skipping adding Mosul role", user.getUsername());
                }
            } else {
                log.info("MosulMetadataUserListener: I am Debugging...");
            }
        } catch (Exception e) {
            log.error("Unable to assign Mosul user Corresponding Role. ", e);
        }
    }

    private void verifyUSerRole(User user, Role role) {
        if (!user.hasRole(role.getRole())) {
            user.addRole(role);
            Context.getUserService().saveUser(user);
            log.info("Granded Mosul user: '{}' Mosul role: '{}'", user.getUsername(), role.getRole());
        } else {
            log.info("Mosul user: '{}' already has Mosul role: '{}''. Skipping...", user.getUsername(), role.getRole());
        }
    }
}
