/* tslint:disable */
export interface PatternDto {
  id: string;
  uri: string;
  name: string;
  iconUrl: string;
  patternLanguageId: string;
  patternLanguageName: string;
  deploymentModelingBehaviorPattern: boolean;
  deploymentModelingStructurePattern: boolean;
  _links: {
    self: { href: string };
    content: { href: string };
    renderedContent: { href: string };
    patternLanguage: { href: string };
  };
}

