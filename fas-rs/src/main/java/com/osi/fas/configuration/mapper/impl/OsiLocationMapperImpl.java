package com.osi.fas.configuration.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.osi.fas.configuration.mapper.OsiLocationMapper;
import com.osi.fas.domain.OsiLocation;
import com.osi.fas.service.dto.OsiLocationDTO;
@Component
public class OsiLocationMapperImpl implements OsiLocationMapper {

	@Override
	public OsiLocationDTO osiLocationToOsiLocationDTO(OsiLocation osiLocation) {
		OsiLocationDTO OsiLocationDTO = new OsiLocationDTO();
		OsiLocationDTO.setLocationId(osiLocation.getLocationId());
		OsiLocationDTO.setLocationCode(osiLocation.getLocationCode());
		OsiLocationDTO.setLocationName(osiLocation.getLocationName());
		OsiLocationDTO.setLocationShortName(osiLocation.getLocationShortName());
		OsiLocationDTO.setAddress1(osiLocation.getAddress1());
		OsiLocationDTO.setAddress2(osiLocation.getAddress2());
		OsiLocationDTO.setCity(osiLocation.getCity());
		OsiLocationDTO.setCountry(osiLocation.getCountry());
		OsiLocationDTO.setCreatedBy(osiLocation.getCreatedBy());
		OsiLocationDTO.setCreatedDate(osiLocation.getCreatedDate());
		OsiLocationDTO.setUpdatedBy(osiLocation.getUpdatedBy());
		OsiLocationDTO.setUpdatedDate(osiLocation.getUpdatedDate());
		OsiLocationDTO.setDateFrom(osiLocation.getDateFrom());
		OsiLocationDTO.setDateTo(osiLocation.getDateTo());
		OsiLocationDTO.setState(osiLocation.getState());
		OsiLocationDTO.setZipcode(osiLocation.getZipcode());
		OsiLocationDTO.setProvince(osiLocation.getProvince());

		return OsiLocationDTO;
	}

	@Override
	public List<OsiLocationDTO> osiLocationsToOsiLocationDTOs(List<OsiLocation> osiLocations) {
		if (osiLocations == null) {
			return null;
		}

		List<OsiLocationDTO> list = new ArrayList<OsiLocationDTO>();
		for (OsiLocation osiLocation : osiLocations) {
			list.add(osiLocationToOsiLocationDTO(osiLocation));
		}

		return list;
	}

	@Override
	public OsiLocation osiLocationDTOToOsiLocation(OsiLocationDTO osiLocationDTO) {
		OsiLocation osiLocation = new OsiLocation();

		osiLocation.setLocationCode(osiLocationDTO.getLocationCode());
		osiLocation.setLocationName(osiLocationDTO.getLocationName());
		osiLocation.setLocationShortName(osiLocationDTO.getLocationShortName());
		osiLocation.setAddress1(osiLocationDTO.getAddress1());
		osiLocation.setAddress2(osiLocationDTO.getAddress2());
		osiLocation.setCity(osiLocationDTO.getCity());
		osiLocation.setCountry(osiLocationDTO.getCountry());
		osiLocation.setCreatedBy(osiLocationDTO.getCreatedBy());
		osiLocation.setCreatedDate(osiLocationDTO.getCreatedDate());
		osiLocation.setUpdatedBy(osiLocationDTO.getUpdatedBy());
		osiLocation.setUpdatedDate(osiLocationDTO.getUpdatedDate());
		osiLocation.setDateFrom(osiLocationDTO.getDateFrom());
		osiLocation.setDateTo(osiLocationDTO.getDateTo());
		osiLocation.setState(osiLocationDTO.getState());
		osiLocation.setZipcode(osiLocationDTO.getZipcode());
		osiLocation.setProvince(osiLocationDTO.getProvince());

		return osiLocation;
	}

	@Override
	public List<OsiLocation> osiLocationDTOsToOsiLocations(List<OsiLocationDTO> osiLocationDTOs) {
		if (osiLocationDTOs == null) {
			return null;
		}

		List<OsiLocation> list = new ArrayList<OsiLocation>();
		for (OsiLocationDTO osiLocationDTO : osiLocationDTOs) {
			list.add(osiLocationDTOToOsiLocation(osiLocationDTO));
		}

		return list;
	}

}
