/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 * @date - 2017年02月18日 12时25分55秒
 */
package com.frank.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frank.base.BaseDao;
import com.frank.base.BaseServiceImpl;
import com.frank.dao.TeamDao;
import com.frank.entity.Team;
import com.frank.service.TeamService;

/**
 * @ClassName:  
 * @Description: 
 * @author collegeistrator
 */

@Service
public class TeamServiceImpl extends BaseServiceImpl<Team> implements TeamService{
	 
	@Autowired
	private TeamDao teamDao;
	@Override
	public BaseDao<Team> getBaseDao() {
		return teamDao;
	}

}
