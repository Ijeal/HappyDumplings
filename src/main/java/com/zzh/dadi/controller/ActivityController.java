package com.zzh.dadi.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.functors.SwitchClosure;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.abel533.entity.Example;
import com.zzh.dadi.dao.GroupDao;
import com.zzh.dadi.dao.IngredientsDao;
import com.zzh.dadi.dao.QuestionnaireDao;
import com.zzh.dadi.dao.UserDao;
import com.zzh.dadi.dto.QuizDto;
import com.zzh.dadi.dto.QuizResult;
import com.zzh.dadi.dto.UserTask;
import com.zzh.dadi.dto.VoteDto;
import com.zzh.dadi.dto.VoteResult;
import com.zzh.dadi.exception.CommonException;
import com.zzh.dadi.po.Group;
import com.zzh.dadi.po.Ingredients;
import com.zzh.dadi.po.Questionnaire;
import com.zzh.dadi.po.User;

@RestController
@RequestMapping(value = "/activity")
public class ActivityController {
    @Autowired
    UserDao userDao;
    @Autowired
    IngredientsDao ingredientsDao;
    @Autowired
    GroupDao groupDao;
    @Autowired
    QuestionnaireDao questionnaireDao;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestBody User user, HttpServletRequest request) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", user.getUsername());
        List<User> userDB = userDao.selectByExample(example);
        if(userDB==null || userDB.size()==0) {
            throw new CommonException("","无此用户！");
        }
        request.getSession().setAttribute("USER", userDB.get(0));
        return userDB.get(0);
    }
    @RequestMapping(value = "/getusertask", method = RequestMethod.GET)
    public UserTask getUser(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("USER");
        if(user==null) {
            throw new CommonException("","用户未登录！");
        }
        UserTask userTask = new UserTask();
        BeanUtils.copyProperties(user, userTask);
        Ingredients ingredients = new Ingredients();
        ingredients.setIngredientsId(user.getIngredients());
        Ingredients ingre = ingredientsDao.selectByPrimaryKey(user.getIngredients());
        if(ingre != null) {
            userTask.setIngredientsName(ingre.getIngredientsname());
            userTask.setOtheringredientsStr(ingre.getDescription());
        }
        Group record = new Group();
        record.setGroupId(user.getUsergroup());
        Group groupDB = groupDao.selectByPrimaryKey(user.getUsergroup());
        if(groupDB != null) userTask.setUsergroupName(groupDB.getGroupname());
        
        return userTask;
    }
    @RequestMapping(value = "/gettaskresult", method = RequestMethod.GET)
    public List<UserTask> gettaskresult(HttpServletRequest request) {
        List<User> userDB = userDao.select(null);
        List<UserTask> userTaskList = new ArrayList();
        for(User user : userDB) {
            UserTask userTask = new UserTask();
            BeanUtils.copyProperties(user, userTask);
            Ingredients ingredients = new Ingredients();
            ingredients.setIngredientsId(user.getIngredients());
            Ingredients ingre = ingredientsDao.selectByPrimaryKey(user.getIngredients());
            if(ingre != null) {
                userTask.setIngredientsName(ingre.getIngredientsname());
                userTask.setOtheringredientsStr(ingre.getDescription());
            }
            Group record = new Group();
            record.setGroupId(user.getUsergroup());
            Group groupDB = groupDao.selectByPrimaryKey(user.getUsergroup());
            if(groupDB != null) userTask.setUsergroupName(groupDB.getGroupname());
            userTaskList.add(userTask);
        }
        return userTaskList;
    }
    @Transactional
    @RequestMapping(value = "/enabletask", method = RequestMethod.POST)
    public UserTask enableTask(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("USER");
        if(user==null) {
            throw new CommonException("","用户未登录！");
        }

        Example example = new Example(Ingredients.class);
        example.createCriteria().andEqualTo("isused", 0);
        List<Ingredients> ingredientsList = ingredientsDao.selectByExample(example);

        //产生0-(arr.length-1)的整数值,也是数组的索引
        int index=(int)(Math.random()*ingredientsList.size());
        Ingredients item = ingredientsList.get(index);
        item.setIsused(1);
        ingredientsDao.updateByPrimaryKey(item);
        Questionnaire questionnaire = questionnaireDao.selectByPrimaryKey(item.getIngredientsId());

  
        user.setIngredients(item.getIngredientsId());
        user.setUsergroup(item.getUsergroup());
        user.setQuizusergroup(questionnaire.getUsergroup());

        Group groupDB = groupDao.selectByPrimaryKey(item.getUsergroup());
        user.setOtheringredients(groupDB.getOtheringredients());
        user.setTaskcardenable(1);
        userDao.updateByPrimaryKey(user);
        
        request.getSession().setAttribute("USER", user);
        UserTask userTask = new UserTask();
        BeanUtils.copyProperties(user, userTask);
        userTask.setIngredientsName(item.getIngredientsname());
        userTask.setUsergroupName(groupDB.getGroupname());
        userTask.setOtheringredientsStr(item.getDescription());
        return userTask;
    }
    @Transactional
    @RequestMapping(value = "/vote", method = RequestMethod.POST)
    public void vote(@RequestBody VoteDto vote, HttpServletRequest request) {
        if("true".equalsIgnoreCase(vote.getIsAdmin())) {
            Group groupDB = groupDao.selectByPrimaryKey(vote.getGroupid());
            groupDB.setVotecount(groupDB.getVotecount()+1);
            groupDao.updateByPrimaryKey(groupDB);
            return;
        }
        User user = (User)request.getSession().getAttribute("USER");
        if(user==null) {
            throw new CommonException("","用户未登录！");
        }
        
        User userDB = userDao.selectByPrimaryKey(user.getUserId());
        if(userDB.getIsvote()==1) throw new CommonException("","用户已投票！");
        userDB.setIsvote(1);
        userDao.updateByPrimaryKey(userDB);
        
        Group groupDB = groupDao.selectByPrimaryKey(vote.getGroupid());
        groupDB.setVotecount(groupDB.getVotecount()+1);
        groupDao.updateByPrimaryKey(groupDB);
    }
    @RequestMapping(value = "/getvoteresult", method = RequestMethod.GET)
    public VoteResult getvoteresult(HttpServletRequest request) {
        User user = (User)request.getSession().getAttribute("USER");
        if(user==null) {
            throw new CommonException("","用户未登录！");
        }
        VoteResult voteResult = new VoteResult();
        List<Group> groupDB = groupDao.select(null);
        for(Group group : groupDB) {
            switch (group.getGroupId()) {
            case 1:
                voteResult.setGroup1(group.getVotecount());
                break;
            case 2:
                voteResult.setGroup2(group.getVotecount());
                break;
            case 3:
                voteResult.setGroup3(group.getVotecount());
                break;
            case 4:
                voteResult.setGroup4(group.getVotecount());
                break;
            case 5:
                voteResult.setGroup5(group.getVotecount());
                break;
            default:
                break;
            }
        }
        return voteResult;
    }
    
    @RequestMapping(value = "/quizstart", method = RequestMethod.POST)
    public void quizstart(HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("USER");
        if(user==null) {
            throw new CommonException("","用户未登录！");
        }
        
        User userDB = userDao.selectByPrimaryKey(user.getUserId());
        if(userDB.getIscorrect() != -1) throw new CommonException("","用户已提交答案！");
        userDB.setTimestampstart(String.valueOf((new Date().getTime())));
        userDao.updateByPrimaryKey(userDB);

    }
    @RequestMapping(value = "/quizsubmit", method = RequestMethod.POST)
    public void quizsubmit(@RequestBody QuizDto quiz,HttpServletRequest request) {

        User user = (User)request.getSession().getAttribute("USER");
        if(user==null) {
            throw new CommonException("","用户未登录！");
        }
        
        User userDB = userDao.selectByPrimaryKey(user.getUserId());
        if(userDB.getIscorrect() != -1) throw new CommonException("","用户已提交答案！");
        userDB.setTimestampend(String.valueOf((new Date().getTime())));
        userDB.setTimeused(String.valueOf((Long.valueOf(userDB.getTimestampend())-Long.valueOf(userDB.getTimestampstart()))));
        Questionnaire questionnaire = questionnaireDao.selectByPrimaryKey(user.getIngredients());
        if(questionnaire.getAnswer().equalsIgnoreCase(quiz.getSelectedStr())) {
            userDB.setIscorrect(1);
        } else {
            userDB.setIscorrect(0);
        }
        userDao.updateByPrimaryKey(userDB);
    }
    
    @RequestMapping(value = "/getquizresult", method = RequestMethod.GET)
    public QuizResult getquizresult(HttpServletRequest request) {
        QuizResult quizResult = new QuizResult();
        Example example1 = new Example(User.class);
        example1.createCriteria().andEqualTo("iscorrect", 1).andEqualTo("quizusergroup", 1);
        example1.setOrderByClause("timeused asc");
        List<User> userDB = userDao.selectByExample(example1);
        if(userDB!=null && userDB.size()>0) {
            quizResult.setGroup1(userDB.get(0).getUsername()+","+Long.valueOf(userDB.get(0).getTimeused())/1000+"秒");
        }
        Example example2 = new Example(User.class);
        example2.createCriteria().andEqualTo("iscorrect", 1).andEqualTo("quizusergroup", 2);
        example2.setOrderByClause("timeused asc");
        userDB = userDao.selectByExample(example2);
        if(userDB!=null && userDB.size()>0) {
            quizResult.setGroup2(userDB.get(0).getUsername()+","+Long.valueOf(userDB.get(0).getTimeused())/1000+"秒");
        }
        Example example3 = new Example(User.class);
        example3.createCriteria().andEqualTo("iscorrect", 1).andEqualTo("quizusergroup", 3);
        example3.setOrderByClause("timeused asc");
        userDB = userDao.selectByExample(example3);
        if(userDB!=null && userDB.size()>0) {
            quizResult.setGroup3(userDB.get(0).getUsername()+","+Long.valueOf(userDB.get(0).getTimeused())/1000+"秒");
        }
        Example example4 = new Example(User.class);
        example4.createCriteria().andEqualTo("iscorrect", 1).andEqualTo("quizusergroup", 4);
        example4.setOrderByClause("timeused asc");
        userDB = userDao.selectByExample(example4);
        if(userDB!=null && userDB.size()>0) {
            quizResult.setGroup4(userDB.get(0).getUsername()+","+Long.valueOf(userDB.get(0).getTimeused())/1000+"秒");
        }
        Example example5 = new Example(User.class);
        example5.createCriteria().andEqualTo("iscorrect", 1).andEqualTo("quizusergroup", 5);
        example5.setOrderByClause("timeused asc");
        userDB = userDao.selectByExample(example5);
        if(userDB!=null && userDB.size()>0) {
            quizResult.setGroup5(userDB.get(0).getUsername()+","+Long.valueOf(userDB.get(0).getTimeused())/1000+"秒");
        }
        return quizResult;
    }
}
