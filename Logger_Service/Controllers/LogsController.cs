using Microsoft.AspNetCore.Mvc;
using Serilog;
using System;

namespace Logger_Service.Controllers
{
    [ApiController]
    [Route("api/logs")]
    public class LogsController : ControllerBase
    {
        [HttpPost]
        public IActionResult CreateLog([FromBody] object log)
        {
            Log.Information("LOG RECEIVED: {@log}", log);
            return Ok();
        }
    }
}
