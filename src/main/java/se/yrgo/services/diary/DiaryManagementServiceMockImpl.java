package se.yrgo.services.diary;

import java.util.*;

import se.yrgo.domain.*;

public class DiaryManagementServiceMockImpl implements DiaryManagementService {

	private Set<Action> allActions = new HashSet<Action>();

	@Override
	public void recordAction(Action action) {
		allActions.add(action);
	}

	public List<Action> getAllIncompleteActions(String requiredUser) {
		List<Action> actionList = new ArrayList<>();

		for (Action action : allActions) {
			if (action.getOwningUser().equals(requiredUser) && !action.isComplete()) {
				actionList.add(action);
			}

		}

		return actionList;
	}

}
