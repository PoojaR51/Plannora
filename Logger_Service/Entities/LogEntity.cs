using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Logger_Service.Entities
{
    [Table("logs")]
    public class LogEntity
    {
        [Key]
        public long Id { get; set; }

        public string ServiceName { get; set; }
        public string Level { get; set; }
        public string Action { get; set; }

        [Column(TypeName = "text")]
        public string Message { get; set; }

        public long? UserId { get; set; }
        public string Role { get; set; }
        public string Email { get; set; }

        public string Method { get; set; }
        public string Path { get; set; }

        [Column(TypeName = "json")]
        public string RequestBody { get; set; }

        [Column(TypeName = "json")]
        public string ResponseBody { get; set; }

        [Column(TypeName = "text")]
        public string Exception { get; set; }

        public DateTime Timestamp { get; set; }
    }
}
