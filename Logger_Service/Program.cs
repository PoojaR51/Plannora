using Serilog;
using Serilog.Sinks.MSSqlServer;
using System.Collections.ObjectModel;

var builder = WebApplication.CreateBuilder(args);

builder.Host.UseSerilog((ctx, lc) =>
{
    lc.WriteTo.Console()
      .WriteTo.File(
          path: "Logs/logger-.log",
          rollingInterval: RollingInterval.Day
      )
      .WriteTo.MSSqlServer(
          connectionString:
              "Data Source=(LocalDB)\\MSSQLLocalDB;Initial Catalog=PlannoraDB;Integrated Security=True;",
          sinkOptions: new MSSqlServerSinkOptions
          {
              TableName = "Logs",
              AutoCreateSqlTable = true
          },
          columnOptions: new ColumnOptions
          {
              AdditionalColumns = new Collection<SqlColumn>()
          }
      );
});

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.MapControllers();
app.Run();
