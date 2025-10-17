
  package aishe.gov.in.client;
  
  import aishe.gov.in.mastersvo.EmailVo; import
  aishe.gov.in.utility.ReturnResponse; import
  org.springframework.cloud.openfeign.FeignClient; import
  org.springframework.web.bind.annotation.PostMapping; import
  org.springframework.web.bind.annotation.RequestBody; import
  org.springframework.web.bind.annotation.RequestParam;
  
  @FeignClient(name = "aishe-user-request", url = "https://"+"${server.feign.client.url}")
  public interface EmailFeignClient {
  
  @PostMapping("/send-any-email") ReturnResponse sendMail(@RequestBody EmailVo mailsend);

  @PostMapping("/send-remuneration-success") ReturnResponse
  sendRemunerationMessage(@RequestParam String templateId, @RequestParam String
  message, @RequestParam String mobile); }
 