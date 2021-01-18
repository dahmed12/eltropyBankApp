package bank.eltropy.domain.transfer;



public interface CreateTransferClient {
    void create(Transfer transfer);

    Transfer getTransferByTitle(String transferTitle);
}
