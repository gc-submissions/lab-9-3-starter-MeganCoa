package co.grandcircus.trackerapi.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import co.grandcircus.trackerapi.model.CountPair;

public class TrackerServiceA implements TrackerService {
	
	ArrayList<CountPair> myList = new ArrayList<>();
	
	//If input token is present in list, that token's count is incremented. If token isn't present, new CountPair is added to the list
	@Override
	public void add(String token) {
		/*
		if(myList.contains(token)) {
			int tokenIndex = myList.indexOf(token);
			myList.get(tokenIndex).setCount(myList.get(tokenIndex).getCount()+1);
		}else {
			myList.add(new CountPair(token, 1));
		}
		*/
		
		boolean needToAddToken = true;
		
		for(int i = 0; i < myList.size(); i++) {
		
			if(myList.get(i).getToken() == token) {
				myList.get(i).setCount(myList.get(i).getCount()+1);
				needToAddToken = false;
			} 
		}
		
		if(needToAddToken = true) {
			myList.add(new CountPair(token, 1));
		}
		
	}
	
	
	@Override
	public void reset() {
		myList.clear();
	}

	@Override
	public int getTotalCount() {
		return myList.size();
	}

	@Override
	public boolean getTokenExists(String token) {
		return myList.contains(token);
	}

	@Override
	public int getTokenCount(String token) {
		int tokenIndex = myList.indexOf(token);
		return myList.get(tokenIndex).getCount();
	}

	@Override
	public String getLatest() {
		
		if(myList.isEmpty()) {
			return "";
		}
		
		int lastIndex = myList.size() - 1;
		return myList.get(lastIndex).getToken();
	}

	@Override
	public CountPair getTop() {
		
		if(myList.isEmpty()) {
			return new CountPair("", 0);
		}
		
		int highestTokenIndex = 0;
		int highestTokenCount = myList.get(0).getCount();
		
		for(int i = 1; i < myList.size() - 1; i++) {
			if(myList.get(i).getCount() > highestTokenCount) {
				highestTokenCount = myList.get(i).getCount();
				highestTokenIndex = i;
			}
		}
		
		return myList.get(highestTokenIndex);
	}

	@Override
	public List<String> getLatest5() {
		
		ArrayList<String> latestFive = new ArrayList<>();
		
		int myListSize = myList.size();
		
		if(myListSize >= 5) {
			latestFive.add(myList.get(myListSize-1).getToken());
			latestFive.add(myList.get(myListSize-2).getToken());
			latestFive.add(myList.get(myListSize-3).getToken());
			latestFive.add(myList.get(myListSize-4).getToken());
			latestFive.add(myList.get(myListSize-5).getToken());
		}
		
		if(myListSize < 5) {
			for(int i = myList.size()-1; i >= 0; i--) {
				latestFive.add(myList.get(i).getToken());
			}
		}
		
		return latestFive;
	}

	@Override
	public List<CountPair> getTop5() {
		
		ArrayList<CountPair> topFive = new ArrayList<>(5);
		
		Comparator<CountPair> countComparator = Comparator.comparing(CountPair::getCount);
		
		topFive = (ArrayList<CountPair>) myList.stream().sorted(countComparator.reversed()).collect(Collectors.toList());
		
		return topFive;
	}

}
