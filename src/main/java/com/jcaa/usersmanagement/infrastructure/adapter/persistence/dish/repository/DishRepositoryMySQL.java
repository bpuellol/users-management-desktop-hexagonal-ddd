package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dish.repository;

import com.jcaa.usersmanagement.application.port.out.dish.DeleteDishPort;
import com.jcaa.usersmanagement.application.port.out.dish.GetAllDishesPort;
import com.jcaa.usersmanagement.application.port.out.dish.GetDishByIdPort;
import com.jcaa.usersmanagement.application.port.out.dish.GetDishByNamePort;
import com.jcaa.usersmanagement.application.port.out.dish.SaveDishPort;
import com.jcaa.usersmanagement.application.port.out.dish.UpdateDishPort;
import com.jcaa.usersmanagement.domain.exception.DishNotFoundException;
import com.jcaa.usersmanagement.domain.model.DishModel;
import com.jcaa.usersmanagement.domain.valueobject.DishId;
import com.jcaa.usersmanagement.domain.valueobject.DishName;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dish.dto.DishPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dish.mapper.DishPersistenceMapper;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class DishRepositoryMySQL
    implements SaveDishPort,
               UpdateDishPort,
               DeleteDishPort,
               GetDishByIdPort,
               GetDishByNamePort,
               GetAllDishesPort {

  private static final String SQL_INSERT =
      "INSERT INTO dishes (id, name, ingredients, created_at, updated_at) "
      + "VALUES (?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE dishes SET name = ?, ingredients = ?, updated_at = NOW() WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, name, ingredients, created_at, updated_at FROM dishes WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_BY_NAME =
      "SELECT id, name, ingredients, created_at, updated_at FROM dishes WHERE name = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, name, ingredients, created_at, updated_at FROM dishes ORDER BY name ASC";

  private static final String SQL_DELETE =
      "DELETE FROM dishes WHERE id = ?";

  private final Connection connection;

  @Override
  public DishModel save(final DishModel dish) {
    final DishPersistenceDto dto = DishPersistenceMapper.fromModelToDto(dish);
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
      stmt.setString(1, dto.id());
      stmt.setString(2, dto.name());
      stmt.setString(3, dto.ingredients());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseSaveFailed(dto.id(), ex);
    }
    return findByIdOrFail(dish.getId());
  }

  @Override
  public DishModel update(final DishModel dish) {
    final DishPersistenceDto dto = DishPersistenceMapper.fromModelToDto(dish);
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
      stmt.setString(1, dto.name());
      stmt.setString(2, dto.ingredients());
      stmt.setString(3, dto.id());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseUpdateFailed(dto.id(), ex);
    }
    return findByIdOrFail(dish.getId());
  }

  @Override
  public void delete(final DishId id) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
      stmt.setString(1, id.value());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseDeleteFailed(id.value(), ex);
    }
  }

  @Override
  public Optional<DishModel> getById(final DishId id) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      stmt.setString(1, id.value());
      final ResultSet rs = stmt.executeQuery();
      if (!rs.next()) return Optional.empty();
      return Optional.of(DishPersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindByIdFailed(id.value(), ex);
    }
  }

  @Override
  public Optional<DishModel> getByName(final DishName name) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_NAME)) {
      stmt.setString(1, name.value());
      final ResultSet rs = stmt.executeQuery();
      if (!rs.next()) return Optional.empty();
      return Optional.of(DishPersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindByIdFailed(name.value(), ex);
    }
  }

  @Override
  public List<DishModel> getAll() {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet rs = stmt.executeQuery();
      return DishPersistenceMapper.fromResultSetToModelList(rs);
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  private DishModel findByIdOrFail(final DishId id) {
    return getById(id)
        .orElseThrow(() -> DishNotFoundException.becauseIdWasNotFound(id.value()));
  }
}
