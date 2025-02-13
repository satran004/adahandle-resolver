package org.cardanofoundation.tools.adahandle.resolver.service;
import com.bloxbean.cardano.yaci.store.utxo.storage.impl.jpa.model.AddressUtxoEntity;
import org.cardanofoundation.tools.adahandle.resolver.entity.AdaHandle;
import org.cardanofoundation.tools.adahandle.resolver.mapper.AddressUtxoAdaHandleMapper;
import org.cardanofoundation.tools.adahandle.resolver.repository.AdaHandleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdaHandleService {
    @Autowired
    private AdaHandleRepository adaHandleRepository;

    public void saveAllAdaHandles(List<AddressUtxoEntity> addressUtxoList) {
        List<AdaHandle> adaHandles = addressUtxoList.stream()
                .map(AddressUtxoAdaHandleMapper::toAdaHandles).flatMap(List::stream)
                .toList();

        adaHandleRepository.saveAll(adaHandles);
        System.out.println(adaHandles);
    }

    public String getStakeAddressByAdaHandle(String adaHandle) {
        List<String> adaHandles = adaHandleRepository.findStakeAddressByAdaHandle(adaHandle);
        if (adaHandles.isEmpty()) {
            return null;
        } else {
            return adaHandles.get(0);
        }
    }
}
