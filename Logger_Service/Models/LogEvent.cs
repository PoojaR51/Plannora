public class LogEvent
{
    public string ServiceName { get; set; }
    public string Level { get; set; }
    public string Action { get; set; }
    public string Message { get; set; }

    public long? UserId { get; set; }
    public string Role { get; set; }
    public string Email { get; set; }

    public string Method { get; set; }
    public string Path { get; set; }

    public object RequestBody { get; set; }
    public object ResponseBody { get; set; }

    public string Exception { get; set; }
    public DateTime Timestamp { get; set; }
}
