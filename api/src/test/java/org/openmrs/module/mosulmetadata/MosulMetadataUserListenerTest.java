package org.openmrs.module.mosulmetadata;

import org.openmrs.Role;
import org.openmrs.User;
import org.openmrs.UserSessionListener;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Context.class, UserService.class})
@PowerMockIgnore({ "javax.management.*", "jdk.internal.reflect.*", "com.sun.*", "javax.*", "org.*" })
public class MosulMetadataUserListenerTest {

    private final String mosulRoleNameTest = MosulMetadataConstants.REGISTRATION_OFFICER_ROLE_NAME;

    @Mock
    private UserService userService;

	@Mock
	private Role mockedMosuRole;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(Context.class);
        PowerMockito.mockStatic(UserService.class);
        when(Context.getService(UserService.class)).thenReturn(userService);
        when(userService.getRole(mosulRoleNameTest)).thenReturn(mockedMosuRole);
    }
	
	@Test
	public void shouldAddCorrespondingRoleToLoggedInUserAtLoggedInOrOut() throws Exception {
		User user = new User();
		user.setUserId(12345);
		user.setUsername("REG_officer");
		MosulMetadataUserListener listener = new MosulMetadataUserListener();
		listener.loggedInOrOut(user, UserSessionListener.Event.LOGIN, UserSessionListener.Status.SUCCESS);

		mockedMosuRole = new Role();
        mockedMosuRole.setName(mosulRoleNameTest);
        when(Context.getUserService().getRole(mosulRoleNameTest)).thenReturn(mockedMosuRole);

		assertEquals(user.hasRole(MosulMetadataConstants.REGISTRATION_OFFICER_ROLE_NAME), true);
	}
}
