package com.techelevator.dao;

import com.techelevator.model.Member;
import com.techelevator.model.Prize;
import com.techelevator.model.ReadingLogDTO;

import java.util.List;

public interface PrizeDao {
   Prize addPrize(Prize prize);
   List<Prize> getListOfPrizes(long familyId);
   Prize getPrizeByPrizeId(long prizeId);
   List<Member> getWinners(int prizeId, int familyId);
   List<ReadingLogDTO> getPrizeParticipants(int prizeId, int familyId);
   public void editPrize(Prize prize, long prizeId);
   public boolean deleteInactivePrize(long prizeId);
}
