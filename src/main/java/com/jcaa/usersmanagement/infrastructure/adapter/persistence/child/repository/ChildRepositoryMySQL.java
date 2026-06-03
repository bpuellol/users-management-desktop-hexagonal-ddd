package com.jcaa.usersmanagement.infrastructure.adapter.persistence.child.repository;

import com.jcaa.usersmanagement.application.port.out.child.DeleteChildPort;
import com.jcaa.usersmanagement.application.port.out.child.GetAllChildrenPort;
import com.jcaa.usersmanagement.application.port.out.child.GetChildByEnrollmentPort;
import com.jcaa.usersmanagement.application.port.out.child.GetChildByIdPort;
import com.jcaa.usersmanagement.application.port.out.child.SaveChildPort;
import com.jcaa.usersmanagement.application.port.out.child.UpdateChildPort;
import com.jcaa.usersmanagement.domain.exception.ChildNotFoundException;
import com.jcaa.usersmanagement.domain.model.ChildModel;
import com.jcaa.usersmanagement.domain.valueobject.ChildId;
import com.jcaa.usersmanagement.domain.valueobject.Enrollment;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.child.dto.ChildPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.child.mapper.ChildPersistenceMapper;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

/**
 * Adaptador de persistencia que conecta el dominio de niños con MySQL mediante JDBC.
 * Implementa todos los puertos de salida definidos en la capa de aplicación.
 */
@Log
@RequiredArgsConstructor
public final class ChildRepositoryMySQL
    implements SaveChildPort,
               UpdateChildPort,
               DeleteChildPort,
               GetChildByIdPort,
               GetChildByEnrollmentPort,
               GetAllChildrenPort {

  private static final String SQL_INSERT =
      "INSERT INTO children "
      + "(id, enrollment, full_name, birth_date, admission_date, discharge_date, status, created_at, updated_at) "
      + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

  private static final String SQL_UPDATE =
      "UPDATE children SET full_name = ?, birth_date = ?, admission_date = ?, "
      + "discharge_date = ?, status = ?, updated_at = NOW() WHERE id = ?";

  private static final String SQL_SELECT_BY_ID =
      "SELECT id, enrollment, full_name, birth_date, admission_date, discharge_date, "
      + "status, created_at, updated_at FROM children WHERE id = ? LIMIT 1";

  private static final String SQL_SELECT_BY_ENROLLMENT =
      "SELECT id, enrollment, full_name, birth_date, admission_date, discharge_date, "
      + "status, created_at, updated_at FROM children WHERE enrollment = ? LIMIT 1";

  private static final String SQL_SELECT_ALL =
      "SELECT id, enrollment, full_name, birth_date, admission_date, discharge_date, "
      + "status, created_at, updated_at FROM children ORDER BY full_name ASC";

  private static final String SQL_DELETE =
      "DELETE FROM children WHERE id = ?";

  private final Connection connection;

  @Override
  public ChildModel save(final ChildModel child) {
    final ChildPersistenceDto dto = ChildPersistenceMapper.fromModelToDto(child);
    executeSave(dto);
    return findByIdOrFail(child.getId());
  }

  @Override
  public ChildModel update(final ChildModel child) {
    final ChildPersistenceDto dto = ChildPersistenceMapper.fromModelToDto(child);
    executeUpdate(dto);
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
      if (!rs.next()) {
        return Optional.empty();
      }
      return Optional.of(ChildPersistenceMapper.fromResultSetToModel(rs));
    } catch (final SQLException ex) {
      throw PersistenceException.becauseFindByIdFailed(id.value(), ex);
    }
  }

  @Override
  public Optional<ChildModel> getByEnrollment(final Enrollment enrollment) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ENROLLMENT)) {
      stmt.setString(1, enrollment.value());
      final ResultSet rs = stmt.executeQuery();
      if (!rs.next()) {
        return Optional.empty();
      }
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

  // ── Helpers ──────────────────────────────────────────────────────────────

  private void executeSave(final ChildPersistenceDto dto) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
      stmt.setString(1, dto.id());
      stmt.setString(2, dto.enrollment());
      stmt.setString(3, dto.fullName());
      stmt.setString(4, dto.birthDate());
      stmt.setString(5, dto.admissionDate());
      stmt.setString(6, dto.dischargeDate());
      stmt.setString(7, dto.status());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseSaveFailed(dto.id(), ex);
    }
  }

  private void executeUpdate(final ChildPersistenceDto dto) {
    try (final PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE)) {
      stmt.setString(1, dto.fullName());
      stmt.setString(2, dto.birthDate());
      stmt.setString(3, dto.admissionDate());
      stmt.setString(4, dto.dischargeDate());
      stmt.setString(5, dto.status());
      stmt.setString(6, dto.id());
      stmt.executeUpdate();
    } catch (final SQLException ex) {
      throw PersistenceException.becauseUpdateFailed(dto.id(), ex);
    }
  }

  private ChildModel findByIdOrFail(final ChildId id) {
    return getById(id)
        .orElseThrow(() -> ChildNotFoundException.becauseIdWasNotFound(id.value()));
  }
}
