package bank.eltropy.domain.transfer;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bank.eltropy.TransferRequest;
import bank.eltropy.domain.Account;
import bank.eltropy.domain.user.User;
import bank.eltropy.domain.user.UserRetrievalClient;
import bank.eltropy.exception.UserNotFound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j

class TransferExecutor {

   public final CreateTransferClient createTransfer;
   public final UserRetrievalClient retreiveUser;
   public final RetreiveAccount retreiveAccount;
   public final TransferRetrievalClient retrieveTransfer;


   public Transfer create(CreateTransferCommand createTransferCommand) {
      return Transfer.generate(createTransferCommand);
   }

   @Transactional
   public void executeAndSaveOrThrow(Transfer transfer) {
      User seller = retreiveUser.findById(transfer.getAuctionOwnerId());
      Account sellerAccount = retreiveAccount.findById(transfer.getAuctionOwnerAccountId())
              .orElseThrow(() -> new AccountNotFound(String.format("Owner account %d doesn't exist", transfer.getAuctionOwnerAccountId())));
      userHasAccountOrThrow(seller, sellerAccount);
      sellerAccount.addAmount(transfer.getAmount());

      try {
         Account winnerAccount = retreiveAccount.findByAccountNumber(transfer.getAuctionWinnerAccountNumber())
                 .orElseThrow(() -> new AccountNotFound(String.format("Winner account %s doesn't exist", transfer.getAuctionWinnerAccountNumber())));
         winnerAccount.subtractAmount(transfer.getAmount());
      } catch (AccountNotFound ex) {
         log.info(String.format("Winner account %d doesn't exist", transfer.getAuctionOwnerAccountId()));
      }

      createTransfer.create(transfer);
   }

   public void userHasAccountOrThrow(User user, Account account) throws UserNotFound {
      if (!user.isAccount(account))
         throw new UserNotFound(String.format("User %d does not have account %d", user.getId(), account.getId()));
   }

   void executePendingTransfers() {
      List<CreateTransferCommand> pendingTransferList = retrieveTransfer.getTransferRequests();
      for (CreateTransferCommand transferRequest : pendingTransferList) {
         executeAndSaveOrThrow(create(transferRequest));
      }
   }

   List<CreateTransferCommand> checkTransfersList(List<TransferRequest> transferRequests) {
      List<CreateTransferCommand> executedTransfersFromProvidedList = new ArrayList<>();
      List<CreateTransferCommand> allExecutedTransfers = retrieveTransfer.getTransferRequests();
      for (TransferRequest transferRequest : transferRequests) {
           CreateTransferCommand command = CreateTransferCommandMapper.map(transferRequest);
         if(allExecutedTransfers.contains(command)) {
            executedTransfersFromProvidedList.add(command);
         }
      }
      return executedTransfersFromProvidedList;
   }
}

