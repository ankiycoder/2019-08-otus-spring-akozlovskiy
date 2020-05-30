package ru.akozlovskiy.useractivity.actuator;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.akozlovskiy.useractivity.domain.UserAcitvity;

@RequiredArgsConstructor
@Component
@Endpoint(id = "activitystat")
public class ActivityStatEndpoint {

	// private final ActivityStatRepository activityStatRepository;

	@ReadOperation
	public List<UserAcitvity> getAppUsersActivityStat() {

		UserAcitvity userAcitvity = new UserAcitvity();
		userAcitvity.setBookName("Name");

		return Collections.singletonList(userAcitvity);
	}
}
