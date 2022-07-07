package com.techelevator.controller;

import com.techelevator.dao.FamilyDao;
import com.techelevator.dao.ReadingLogDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Member;
import com.techelevator.model.ReadingLog;
import com.techelevator.model.ReadingLogDTO;
import com.techelevator.model.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class ReadingLogController {

    @Autowired
    private ReadingLogDao readingLogDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private FamilyDao familyDao;


    @ApiOperation("get reading log by Id")
    @RequestMapping(path="/readingLog/{id}", method = RequestMethod.GET)
    public ReadingLog getReadingLogByReadingId(@PathVariable  @ApiParam("readingLog ") int id) {

        return readingLogDao.getReadingLogById(id);

    }


    @ApiOperation("get readinglog by userId")
    @RequestMapping(path="/{userId}/readingLog", method = RequestMethod.GET)
    public List<ReadingLog> getReadingByUserId(@PathVariable @ApiParam("readinglog object") int userId) {

        return readingLogDao.getReadingLogByUserId(userId);

    }

    @ApiOperation("get readinglog by userId")
    @RequestMapping(path="/display/{userId}", method = RequestMethod.GET)
    public List<ReadingLogDTO> getReadingDTOByUserId(@PathVariable @ApiParam("readinglog object") int userId) {

        return readingLogDao.getReadingLogDTOByUserId(userId);

    }

    @ApiOperation("get readinglog by familyId")
    @RequestMapping(path="/myFamily/readingLog", method = RequestMethod.GET)
    public List<ReadingLog> getReadingByFamilyId(@ApiParam("readinglog object") Principal principal) {

       int userId = userDao.findIdByUsername(principal.getName());
       int familyId = (int)familyDao.getFamilyIDByUserId((long)userId);


        return readingLogDao.getReadingLogByFamilyId(familyId);

    }
    @ApiOperation("adding a new readinglog")
    @RequestMapping(path="/add-log", method = RequestMethod.POST)
    public ReadingLog addReadingLog(@RequestBody @ApiParam("Readinglog object") ReadingLog readingLog, Principal principal) {

        return readingLogDao.addNewReadingLog(readingLog);

    }

    @ApiOperation("get total reading time by userId")
    @RequestMapping(path="/readingLogTime/{userId}", method = RequestMethod.GET)
    public Integer getTotalReadingTimeByUser(@PathVariable @ApiParam("Reading log object") int userId) {

        //long user_id = member.getUserId();

        return readingLogDao.getTotalReadingTime(userId);

    }

//    @ApiOperation("get reading log by Id")
//    @RequestMapping(path="/readingLog/{id}", method = RequestMethod.GET)
//    public ReadingLog getReadingLogByReadingId(@PathVariable  @ApiParam("readingLog ") int id) {
//
//        return readingLogDao.getReadingLogById(id);
//
//    }

}
