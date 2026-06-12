package com.jcaa.usersmanagement.infrastructure.adapter.persistence.child.repository;

import com.jcaa.usersmanagement.application.port.out.child.DeleteChildPort;
import com.jcaa.usersmanagement.application.port.out.child.GetAllChildrenPort;
import com.jcaa.usersmanagement.application.port.out.child.GetAllChildrenWithStatusPort;
import com.jcaa.usersmanagement.application.port.out.child.GetChildByEnrollmentPort;
import com.jcaa.usersmanagement.application.port.out.child.GetChildByIdPort;
import com.jcaa.usersmanagement.application.port.out.child.GetInactiveChildrenPort;
import com.jcaa.usersmanagement.application.port.out.child.SaveChildPort;
import com.jcaa.usersmanagement.application.port.out.child.UpdateChildPort;
import com.jcaa.usersmanagement.domain.exception.ChildNotFoundException;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.Enrollment;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.child.mapper.ChildPersistenceMapper;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.entrypoint.desktop.child.dto.ChildStatusResponse;
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
public final class ChildRepositoryMySQL
    implements SaveChildPort,
               UpdateChildPort,
               DeleteChildPort,
               GetChildByIdPort,
               GetChildByEnrollmentPort,
               GetAllChildrenPort,
               GetAllChildrenWithStatusPort,
               GetInactiveChildrenPort {

  // ── CRUDL SQL ──────────────────────────────────────────
  private static final String SQL_INSERT =
      "INSERT INTO children (id, enrollment, full_name, birth_date, admission_date, "
      + "discharge_date, status, created_at, updated_at) "
      + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE children SET enrollment = ?, full_name = ?, birth_date = ?, "
      + "admission_date = ?, discharge_date = ?, status = ?, updated_at = NOW() "
      + "WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, enrollment, full_name, birth_date, admission_date, "
      + "discharge_date, status, created_at, updated_at "
      + "FROM children WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_BY_ENROLLMENT =
      "SELECT id, enrollment, full_name, birth_date, admission_date, "
      + "discharge_date, status, created_at, updated_at "
      + "FROM children WHERE enrollment = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, enrollment, full_name, birth_date, admission_date, "
      + "discharge_date, status, created_at, updated_at "
      + "FROM children ORDER BY full_name ASC";

  private static final String SQL_DELETE =
      "DELETE FROM children WHERE id = ?";

  // ── CEA Queries SQL ────────────────────────────────────

  /** CEA #1 — todos los niños con su estado */
  private static final String SQL_ALL_WITH_STATUS =
      "SELECT id, enrollment, full_name, birth_date, admission_date, "
      + "discharge_date, status "
      + "FROM children ORDER BY full_name ASC";

  /** CEA #2 — solo los niños dados de baja */
  private static final String SQL_INACTIVE =
      "SELECT id, enrollment, full_name, birth_date, admission_date, "
      + "discharge_date, status "
      + "FROM children WHERE status = 'INACTIVE' ORDER BY discharge_date DESC";

  private final Connection connection;

  // ── CRUDL ──────────────────────────────────────────────

  @Override
  public ChildModel save(final ChildModel child) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
      stmt.setString(1, child.getId().value());
      stmt.setString(2, child.getEnrollment().value());
      stmt.setString(3, child.getFullName().value());
      stmt.setString(4, child.getBirthDate().toString());
      stmt.setString(5, child.getAdmissionDate().toString());
      stmt.setString(6, child.getDischargeDate() != null
          ? child.getDischargeDate().toString() : null);
      stmt.setString(7, child.getStatus().name());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseSaveFailed(child.getId().value(), ex);
    }
    return findByIdOrFail(child.getId());
  }

  @Override
  public ChildModel update(final ChildModel child) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
      stmt.setString(1, child.getEnrollment().value());
      stmt.setString(2, child.getFullName().value());
      stmt.setString(3, child.getBirthDate().toString());
      stmt.setString(4, child.getAdmissionDate().toString());
      stmt.setString(5, child.getDischargeDate() != null
          ? child.getDischargeDate().toString() : null);
      stmt.setString(6, child.getStatus().name());
      stmt.setString(7, child.getId().value());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseUpdateFailed(child.getId().value(), ex);
    }
    return findByIdOrFail(child.getId());
  }

  @Override
  public void delete(final ChildId id) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
      stmt.setString(1, id.value());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseDeleteFailed(id.value(), ex);
    }
  }

  @Override
  public Optional<ChildModel> getById(final ChildId id) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID)) {
      stmt.setString(1, id.value());
      final ResultSet rs = stmt.executeQuery();
      if (!rs.next()) return Optional.empty();
      return Optional.of(ChildPersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindByIdFailed(id.value(), ex);
    }
  }

  @Override
  public Optional<ChildModel> getByEnrollment(final Enrollment enrollment) {
    try (final PreparedStatement stmt =
        connection.prepareStatement(SQL_SELECT_BY_ENROLLMENT)) {
      stmt.setString(1, enrollment.value());
      final ResultSet rs = stmt.executeQuery();
      if (!rs.next()) return Optional.empty();
      return Optional.of(ChildPersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindByIdFailed(enrollment.value(), ex);
    }
  }

  @Override
  public List<ChildModel> getAll() {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ALL)) {
      final ResultSet rs = stmt.executeQuery();
      return ChildPersistenceMapper.fromResultSetToModelList(rs);
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  // ── CEA Queries ────────────────────────────────────────

  @Override
  public List<ChildStatusResponse> getAllChildrenWithStatus() {
    try (final PreparedStatement stmt =
        connection.prepareStatement(SQL_ALL_WITH_STATUS)) {
      final ResultSet rs = stmt.executeQuery();
      return mapStatusRows(rs);
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  @Override
  public List<ChildStatusResponse> getInactiveChildren() {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_INACTIVE)) {
      final ResultSet rs = stmt.executeQuery();
      return mapStatusRows(rs);
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindAllFailed(ex);
    }
  }

  // ── Helpers ────────────────────────────────────────────

  private List<ChildStatusResponse> mapStatusRows(final ResultSet rs) throws SQLException {
    final List<ChildStatusResponse> result = new ArrayList<>();
    while (rs.next()) {
      result.add(new ChildStatusResponse(
          rs.getString("id"),
          rs.getString("enrollment"),
          rs.getString("full_name"),
          rs.getString("birth_date"),
          rs.getString("admission_date"),
          rs.getString("discharge_date") != null ? rs.getString("discharge_date") : "-",
          rs.getString("status")));
    }
    return result;
  }

  private ChildModel findByIdOrFail(final ChildId id) {
    return getById(id)
        .orElseThrow(() -> ChildNotFoundException.becauseIdWasNotFound(id.value()));
  }
}
