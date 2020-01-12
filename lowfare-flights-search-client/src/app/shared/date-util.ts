export default class DateUtil {
  static toIsoString(val: Date): string {
    return new Date(val.getTime() - (val.getTimezoneOffset() * 60000)).toISOString().substring(0, 10);
  }
}
