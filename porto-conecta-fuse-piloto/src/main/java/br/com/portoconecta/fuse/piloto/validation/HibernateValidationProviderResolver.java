package br.com.portoconecta.fuse.piloto.validation;

import java.util.Collections;
import java.util.List;

import javax.validation.ValidationProviderResolver;
import javax.validation.spi.ValidationProvider;

import org.hibernate.validator.HibernateValidator;

public class HibernateValidationProviderResolver implements ValidationProviderResolver{

	@Override
	public List<ValidationProvider<?>> getValidationProviders() {
		return Collections.singletonList(new HibernateValidator());
	}


}
