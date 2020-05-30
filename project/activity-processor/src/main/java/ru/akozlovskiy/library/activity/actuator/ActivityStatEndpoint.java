package ru.akozlovskiy.library.activity.actuator;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.akozlovskiy.library.activity.domain.UserAcitvity;

@RequiredArgsConstructor
@Component
@Endpoint(id = "activity-stat")
public class ActivityStatEndpoint {

	// private final ActivityStatRepository activityStatRepository;

	@ReadOperation
	public List<UserAcitvity> getAppUsersActivityStat() {

		UserAcitvity userAcitvity = new UserAcitvity("appUserName", "activityType", 1);

		return Collections.singletonList(userAcitvity);
	}
}
