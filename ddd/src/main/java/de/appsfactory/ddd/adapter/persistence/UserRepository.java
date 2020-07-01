package de.appsfactory.ddd.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<UserJpaEntity, Long>  {
}
