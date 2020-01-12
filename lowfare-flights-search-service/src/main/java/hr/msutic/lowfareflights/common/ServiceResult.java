package hr.msutic.lowfareflights.common;

import lombok.Getter;

import java.util.List;

/**
 * @author c214223
 */
@Getter
public class ServiceResult<T> {

  private boolean success;
  private List<String> messages;
  private T result;

  public ServiceResult(boolean success, T result) {
    this.success = success;
    this.result = result;
  }

}
