using Logger_Service.Entities;
using Microsoft.EntityFrameworkCore;

namespace Logger_Service.Data
{
	public class LoggerDbContext : DbContext
	{
		public LoggerDbContext(DbContextOptions<LoggerDbContext> options)
			: base(options) { }

		public DbSet<LogEntity> Logs { get; set; }
	}
}
