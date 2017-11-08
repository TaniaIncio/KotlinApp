package com.tincio.pharmaapp.domain.callback;


import com.tincio.pharmaapp.data.service.request.MedicosRequest;
import com.tincio.pharmaapp.data.service.response.MedicosResponse;

import java.util.List;

public interface MedicosCallback {

    void onResponse(Boolean status, String message, List<MedicosResponse> medicos);
}
