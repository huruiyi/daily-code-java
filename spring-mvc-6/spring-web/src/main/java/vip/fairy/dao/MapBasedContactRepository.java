package vip.fairy.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import vip.fairy.repos.ContactRepository;
import vip.fairy.model.Contact;

@Primary
@Service
public class MapBasedContactRepository implements ContactRepository {

	private final AtomicLong SEQUENCE = new AtomicLong();
	private Map<Long, Contact> contacts = new HashMap<>();

	@Override
	public List<Contact> findAll() {
		return new ArrayList<>(contacts.values());
	}

	@Override
	public void save(Contact c) {
		if (c.getId() <= 0) {
			c.setId(SEQUENCE.incrementAndGet());
		}
		contacts.put(c.getId(), c);
	}
}
