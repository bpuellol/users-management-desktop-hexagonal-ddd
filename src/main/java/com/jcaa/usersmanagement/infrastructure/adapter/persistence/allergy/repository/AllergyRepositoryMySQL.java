package com.jcaa.usersmanagement.infrastructure.adapter.persistence.allergy.repository;

import com.jcaa.usersmanagement.application.port.out.allergy.DeleteAllergyPort;
import com.jcaa.usersmanagement.application.port.out.allergy.GetAllAllergiesPort;
import com.jcaa.usersmanagement.application.port.out.allergy.GetAllergyByIdPort;
import com.jcaa.usersmanagement.application.port.out.allergy.GetAllergyByIngredientAndChildPort;
import com.jcaa.usersmanagement.application.port.out.allergy.GetChildrenWithAllergiesPort;
import com.jcaa.usersmanagement.application.port.out.allergy.GetForbiddenDishesByChildPort;
import com.jcaa.usersmanagement.application.port.out.allergy.SaveAllergyPort;
import com.jcaa.usersmanagement.application.port.out.allergy.UpdateAllergyPort;
import com.jcaa.usersmanagement.domain.exception.AllergyNotFoundException;
import com.jcaa.usersmanagement.domain.model.AllergyModel;
import com.jcaa.usersmanagement.domain.valueobject.AllergyId;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.Ingredient;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.allergy.dto.AllergyPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.allergy.mapper.AllergyPersistenceMapper;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ChildAllergyResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.allergy.dto.ForbiddenDishResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@RequiredArgsConstructor
public final class AllergyRepositoryMySQL
    implements SaveAllergyPort,
               UpdateAllergyPort,
               DeleteAllergyPort,
               GetAllergyByIdPort,
               GetAllergyByIngredientAndChildPort,
               GetAllAllergiesPort,
               GetChildrenWithAllergiesPort,
               GetForbiddenDishesByChildPort {

  // ── CRUDL SQL ──────────────────────────────────────────
  private static final String SQL_INSERT =
      "INSERT INTO allergies (id, child_id, ingredient, severity, notes, created_at, updated_at) "
      + "VALUES (?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE allergies SET ingredient = ?, severity = ?, notes = ?, updated_at = NOW() "
      + "WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, child_id, ingredient, severity, notes, created_at, updated_at "
      + "FROM allergies WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_BY_INGREDIENT_AND_CHILD =
      "SELECT id, child_id, ingredient, severity, notes, created_at, updated_at "
      + "FROM allergies WHERE ingredient = ? AND child_id = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, child_id, ingredient, severity, notes, created_at, updated_at "
      + "FROM allergies ORDER BY ingredient ASC";

  private static final String SQL_DELETE =
      "DELETE FROM allergies WHERE id = ?";

  // ── CEA Queries SQL ────────────────────────────────────

  /** CEA #7 — niños con alergias e ingredientes prohibidos */
  private static final String SQL_CHILDREN_WITH_ALLERGIES =
      "SELECT c.id AS child_id, c.enrollment, c.full_name, "
      + "a.ingredient, a.severity, a.notes "
      + "FROM allergies a "
      + "INNER JOIN children c ON a.child_id = c.id "
      + "ORDER BY c.full_name ASC, a.ingredient ASC";

  /** CEA #9 — platos que NO puede comer cada niño por sus alergias */
  private static final String SQL_FORBIDDEN_DISHES =
      "SELECT c.id AS child_id, c.enrollment, c.full_name, "
      + "a.ingredient AS allergen_ingredient, a.severity, "
      + "d.id AS dish_id, d.name AS dish_name, d.ingredients AS dish_ingredients "
      + "FROM allergies a "
      + "INNER JOIN children c ON a.child_id = c.id "
      + "INNER JOIN dishes d ON FIND_IN_SET(a.ingredient, "
      + "  REPLACE(REPLACE(d.ingredients, ', ', ','), ' ,', ',')) > 0 "
      + "ORDER BY c.full_name ASC, d.name ASC";

  private final Connection connection;

  // ── CRUDL ──────────────────────────────────────────────

  @Override
  public AllergyModel save(final AllergyModel allergy) {
    final AllergyPersistenceDto dto = AllergyPersistenceMapper.fromModelToDto(allergy);
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
      stmt.setString(1, dto.id());
      stmt.setString(2, dto.childId());
      stmt.setString(3, dto.ingredient());
      stmt.setString(4, dto.severity());
      stmt.setString(5, dto.notes());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseSaveFailed(dto.id(), ex);
    }
    return findByIdOrFail(allergy.getId());
  }

  @Override
  public AllergyModel update(final AllergyModel allergy) {
    final AllergyPersistenceDto dto = AllergyPersistenceMapper.fromModelToDto(allergy);
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
      stmt.setString(1, dto.ingredient());
      stmt.setString(2, dto.severity());
      stmt.setString(3, dto.notes());
      stmt.setString(4, dto.id());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseUpdateFailed(dto.id(), ex);
    }
    return findByIdOrFail(allergy.getId());
  }

  @Override
  public void delete(final AllergyId id) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
      stmt.setString(1, id.value());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseDeleteFailed(id.value(), ex);
    }
  }

  @Override
  public Optional<AllergyModel> getById(final AllergyId id) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      stmt.setString(1, id.value());
      final ResultSet rs = stmt.executeQuery();
      if (!rs.next()) return Optional.empty();
      return Optional.of(AllergyPersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindByIdFailed(id.value(), ex);
    }
  }

  @Override
  public Optional<AllergyModel> getByIngredientAndChild(
      final Ingredient ingredient, final ChildId childId) {
    try (final PreparedStatement stmt =
        connection.prepareStatement(SQL_SELECT_BY_INGREDIENT_AND_CHILD)) {
      stmt.setString(1, ingredient.value());
      stmt.setString(2, childId.value());
      final ResultSet rs = stmt.executeQuery();
      if (!rs.next()) return Optional.empty();
      return Optional.of(AllergyPersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindByIdFailed(ingredient.value(), ex);
    }
  }

  @Override
  public List<AllergyModel> getAll() {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet rs = stmt.executeQuery();
      return AllergyPersistenceMapper.fromResultSetToModelList(rs);
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  // ── CEA Queries ────────────────────────────────────────

  @Override
  public List<ChildAllergyResponse> getChildrenWithAllergies() {
    try (final PreparedStatement stmt =
        connection.prepareStatement(SQL_CHILDREN_WITH_ALLERGIES)) {
      final ResultSet rs = stmt.executeQuery();
      final List<ChildAllergyResponse> result = new ArrayList<>();
      while (rs.next()) {
        result.add(new ChildAllergyResponse(
            rs.getString("child_id"),
            rs.getString("enrollment"),
            rs.getString("full_name"),
            rs.getString("ingredient"),
            rs.getString("severity"),
            rs.getString("notes") != null ? rs.getString("notes") : "-"));
      }
      return result;
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  @Override
  public List<ForbiddenDishResponse> getForbiddenDishesByChild() {
    try (final PreparedStatement stmt =
        connection.prepareStatement(SQL_FORBIDDEN_DISHES)) {
      final ResultSet rs = stmt.executeQuery();
      final List<ForbiddenDishResponse> result = new ArrayList<>();
      while (rs.next()) {
        result.add(new ForbiddenDishResponse(
            rs.getString("child_id"),
            rs.getString("enrollment"),
            rs.getString("full_name"),
            rs.getString("allergen_ingredient"),
            rs.getString("severity"),
            rs.getString("dish_id"),
            rs.getString("dish_name"),
            rs.getString("dish_ingredients")));
      }
      return result;
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  private AllergyModel findByIdOrFail(final AllergyId id) {
    return getById(id)
        .orElseThrow(() -> AllergyNotFoundException.becauseIdWasNotFound(id.value()));
  }
}
