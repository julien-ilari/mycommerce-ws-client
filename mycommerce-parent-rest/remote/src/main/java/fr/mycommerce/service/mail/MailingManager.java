package fr.mycommerce.service.mail;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.eclipse.microprofile.config.inject.ConfigProperty;

/***
 * <h1>Service Courrier électronique</h1>
 * <p>
 * Le service ne garantit pas l'acheminement du courriel à bon port. Un message
 * peut être perdu, ou retardé.
 * </p>
 * <p>
 * Les notifications de réception et de non-réception sont prévues dans la
 * norme, mais de rares logiciels de courriel ne les proposent pas, ou ne les
 * honorent pas en réception, ou envoient l'accusé de réception sans en prévenir
 * le lecteur.
 * </p>
 * 
 * @author Julien ILARI
 *
 */
@ApplicationScoped
public class MailingManager {
	
	@Inject
    private MailRepository repository;

	@Inject
	@ConfigProperty(name = "mail.identifiant")
	private String senderEmail;

	@Inject
	@ConfigProperty(name = "mail.password")
	private String senderPassword;
	
	@Inject
	@ConfigProperty(name = "mail.smtp.host")
	private String smtpHost;
	
	@Inject
	@ConfigProperty(name = "mail.smtp.port")
	private String smtpPort;
	
	@Inject
	@ConfigProperty(name = "mail.smtp.auth")
	private String smtpAuth;
	
	@Inject
	@ConfigProperty(name = "mail.smtp.starttls.enable")
	private String smtpStarttls;
	
	@Inject
	@ConfigProperty(name = "mail.session.debug")
	private boolean sessionDebug;
	
	
	/**
	 * Lite de tous les messages
	 * @return
	 */
	public List<MailDTO> list()
	{
		return repository.findAll();
	}
	

	/**
	 * <h1>Envoyer un nouveau message</h1>
	 * Point d'entrée pour envoyer un nouveau message
	 * @param to destinataire
	 * @param title objet du message
	 * @param html contenu du message au format html
	 * @throws MessagingException
	 */
	@Transactional
	public synchronized MailDTO sendAsHtml(@NotNull  String from, @NotNull  String objet, @NotNull String html) throws MessagingException {
		System.out.println("Sending email from " + from);
		Session session = createSession();

		// Création du message utilisant la session
		final MimeMessage message = new MimeMessage(session);
		prepareEmailMessage(message, from, objet, html);

		// Envoi du message
		//Transport.send(message);
		
		// sauvegarde du mail
		MailDTO dto = new MailDTO();
		dto.setId(list().size() + 1L);
		dto.setTransmitter(from);
		dto.setObjet(objet);
		
		dto.setSujet("Sujet");
		dto.setDateSending(new Date());
		dto.setMessage(html);
		
		repository.save(dto);
		
		return dto;
	}

	private void prepareEmailMessage(final MimeMessage message, final String to, final String title, final String html)
			throws MessagingException {
		message.setContent(html, "text/html; charset=utf-8");
		message.setFrom(new InternetAddress(senderEmail));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		message.setSubject(title);
	}

	/**
	 * Création de la Session
	 * @return
	 */
	private Session createSession() {
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpHost);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.smtp.auth", smtpAuth);
		props.put("mail.smtp.starttls.enable", smtpStarttls);

		final Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(senderEmail, senderPassword);
			}
		});
		
		// Used to debug SMTP issues
        session.setDebug(sessionDebug);

		return session;
	}
}