package bank.eltropy.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import bank.eltropy.TransferRequest;
import bank.eltropy.domain.transfer.CreateTransferCommand;
import bank.eltropy.domain.transfer.CreateTransferCommandMapper;
import bank.eltropy.domain.transfer.TransferRetrievalClient;

@Service
class TransferRetrievalRestClient implements TransferRetrievalClient {

	private final RestTemplate restTemplate;
	private final String pendingTransfersUrl;

	@Autowired
	public TransferRetrievalRestClient(RestTemplate restTemplate,
			@Value("${pending.transfers.retrieval.eltropybankapp.url}") String pendingTransferUrl) {
		this.restTemplate = restTemplate;
		this.pendingTransfersUrl = pendingTransferUrl;
	}

	@Override
	public List<CreateTransferCommand> getTransferRequests() {
		List<TransferRequest> result = restTemplate.exchange(pendingTransfersUrl, HttpMethod.GET, HttpEntity.EMPTY,
				new ParameterizedTypeReference<List<TransferRequest>>() {
				}).getBody();
		return result.stream().map(CreateTransferCommandMapper::map).collect(Collectors.toList());
	}
}
